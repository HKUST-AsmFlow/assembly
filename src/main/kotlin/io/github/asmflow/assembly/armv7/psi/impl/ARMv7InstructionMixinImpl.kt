package io.github.asmflow.assembly.armv7.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import io.github.asmflow.assembly.armv7.database.ARMv7InstructionDatabase
import io.github.asmflow.assembly.armv7.execution.ARMv7InstructionConditionCode
import io.github.asmflow.assembly.armv7.psi.ARMv7Instruction
import io.github.asmflow.assembly.util.functional.*

abstract class ARMv7InstructionMixinImpl(node: ASTNode) : ASTWrapperPsiElement(node), ARMv7Instruction {
    override val baseMnemonic: String
        get() = partitionMnemonic().base

    override val setsFlags: Boolean
        get() = partitionMnemonic().setsFlags

    override val conditionCode: ARMv7InstructionConditionCode
        get() = partitionMnemonic().condition.unwrapOr(ARMv7InstructionConditionCode.AL)

    /**
     * One way of splitting a written mnemonic into `<base>{S}{<cond>}`.
     */
    private data class MnemonicParts(
        val base: String,
        val setsFlags: Boolean,
        val condition: Option<ARMv7InstructionConditionCode>
    )

    /**
     * Splits a written mnemonic into its base, its optional `S` (set-flags) suffix and its
     * optional condition-code suffix, following the UAL order `<base>{S}{<cond>}`.
     *
     * The split has to be database-driven rather than purely textual, because several real
     * mnemonics *end* in text that also spells a suffix: `svc` and `teq` end in the condition
     * codes `vc` and `eq`, `mrs` ends in `s`, and `smlal`, `umlal` and `umaal` all end in `al`.
     * Stripping those blindly leaves a base that names no instruction at all -- `svc` used to
     * decompose into base `""` with `S` set and condition `VC`, which is why it could never be
     * assembled.
     *
     * So instead every candidate split is tried from the most literal to the least, and the
     * first one whose base actually names a known instruction wins. The database's
     * `supportsFlags` and `supportsConditionCodes` entries are what disambiguate the genuinely
     * ambiguous mnemonics: `bls` could be `bl` + `S` or `b` + `LS`, and only the latter
     * survives, because `bl` does not set flags.
     *
     * Candidates are matched in two passes. The strict pass requires the base to permit the
     * suffixes being stripped; the lenient pass drops that requirement. The lenient pass exists
     * purely for error messages -- it lets `bxs` resolve to `bx` + `S` so the assembler can say
     * "does not support the S suffix" instead of the far less helpful "bxs is not supported".
     *
     * If nothing matches, the whole text is returned as the base, so an unknown mnemonic is
     * reported under the name the user actually wrote.
     */
    private fun partitionMnemonic(): MnemonicParts {
        val text = mnemonic.text.lowercase()
        val candidates = candidateSplits(text)

        return candidates.firstOrNull { it.isWellFormed(strict = true) }
            ?: candidates.firstOrNull { it.isWellFormed(strict = false) }
            ?: MnemonicParts(text, setsFlags = false, condition = None)
    }

    /**
     * Enumerates the ways [text] could be read, most literal first.
     */
    private fun candidateSplits(text: String): List<MnemonicParts> = buildList {
        // <base>
        add(MnemonicParts(text, setsFlags = false, condition = None))

        // <base>S
        if (text.length > 1 && text.endsWith('s')) {
            add(MnemonicParts(text.dropLast(1), setsFlags = true, condition = None))
        }

        if (text.length > 2) {
            val condition = ARMv7InstructionConditionCode.fromString(text.takeLast(2)).toOption()
            if (condition.isSome()) {
                val withoutCondition = text.dropLast(2)

                // <base><cond>
                add(MnemonicParts(withoutCondition, setsFlags = false, condition = condition))

                // <base>S<cond>
                if (withoutCondition.length > 1 && withoutCondition.endsWith('s')) {
                    add(MnemonicParts(withoutCondition.dropLast(1), setsFlags = true, condition = condition))
                }
            }
        }
    }

    /**
     * Whether this split names a real instruction. Under [strict], that instruction must also
     * permit whichever suffixes the split stripped off.
     */
    private fun MnemonicParts.isWellFormed(strict: Boolean): Boolean {
        if (base.isEmpty()) return false

        val entry = ARMv7InstructionDatabase.get(base)
        if (entry.isNone()) return false
        if (!strict) return true

        val instruction = entry.unwrap()
        if (setsFlags && !instruction.supportsFlags) return false
        if (condition.isSome() && !instruction.supportsConditionCodes) return false

        return true
    }
}
