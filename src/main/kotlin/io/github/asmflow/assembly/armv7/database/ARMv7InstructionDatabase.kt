package io.github.asmflow.assembly.armv7.database

import io.github.asmflow.assembly.armv7.psi.ARMv7Instruction
import io.github.asmflow.assembly.openapi.BundledXmlDatabase
import io.github.asmflow.assembly.util.functional.Option
import io.github.asmflow.assembly.util.functional.toOption
import org.jdom.Document

object ARMv7InstructionDatabase :
    BundledXmlDatabase<String, ARMv7InstructionDatabase.Instruction>("/armv7/InstructionDatabase.xml") {
    data class Instruction(val mnemonic: String, val supportsFlags: Boolean, val supportsConditionCodes: Boolean)

    override fun parseDocument(document: Document): Map<String, Instruction> {
        require(document.rootElement.name == "instructions")

        val root = document.rootElement
        return root.getChildren("instruction").associate { element ->
            val mnemonic = element.getAttribute("name").value
            val supportsFlags = element.getAttribute("flags").toOption().map { it.booleanValue }.unwrapOr(false)
            val supportsConditionCodes =
                element.getAttribute("conditioncodes").toOption().map { it.booleanValue }.unwrapOr(false)

            mnemonic to Instruction(mnemonic, supportsFlags, supportsConditionCodes)
        }
    }

    operator fun get(instruction: ARMv7Instruction): Option<Instruction> =
        data.entries.firstOrNull { instruction.mnemonic.textMatches(it.key) }?.value.toOption()
}
