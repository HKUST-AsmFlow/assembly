package io.github.asmflow.assembly.armv7.assembler

import io.github.asmflow.assembly.armv7.assembler.utils.ARMv7Immediate
import io.github.asmflow.assembly.armv7.database.ARMv7InstructionDatabase.getOpcode
import io.github.asmflow.assembly.armv7.execution.ARMv7InstructionConditionCode
import io.github.asmflow.assembly.armv7.execution.ARMv7InstructionOperand
import io.github.asmflow.assembly.armv7.execution.ARMv7Register
import io.github.asmflow.assembly.armv7.psi.ARMv7InstructionMixin
import io.github.asmflow.assembly.armv7.psi.ARMv7Operand
import io.github.asmflow.assembly.armv7.util.functional.Option

class ARMv7MemoryAccessEncoder(val symbols: HashMap<String, Int>) : ARMv7InstructionEncoder {
    private val loadStoreMnemonics = setOf("ldr", "str")

    private fun Boolean.toInt() = if (this) 1 else 0

    private enum class BlockAddressingMode(val preIndexed: Boolean, val add: Boolean) {
        IA(preIndexed = false, add = true),
        IB(preIndexed = true, add = true),
        DA(preIndexed = false, add = false),
        DB(preIndexed = true, add = false),
    }

    private data class BlockTransfer(val mode: BlockAddressingMode, val load: Boolean)

    companion object {
        private val stackMnemonics = setOf("push", "pop")

        // Stack aliases are named after the stack they build, so loads and stores map to opposite modes
        // (e.g. STMFD is decrement-before, LDMFD is increment-after).
        private val blockTransfers = mapOf(
            "ldm" to BlockTransfer(BlockAddressingMode.IA, load = true),
            "ldmia" to BlockTransfer(BlockAddressingMode.IA, load = true),
            "ldmib" to BlockTransfer(BlockAddressingMode.IB, load = true),
            "ldmda" to BlockTransfer(BlockAddressingMode.DA, load = true),
            "ldmdb" to BlockTransfer(BlockAddressingMode.DB, load = true),
            "ldmfd" to BlockTransfer(BlockAddressingMode.IA, load = true),
            "ldmed" to BlockTransfer(BlockAddressingMode.IB, load = true),
            "ldmfa" to BlockTransfer(BlockAddressingMode.DA, load = true),
            "ldmea" to BlockTransfer(BlockAddressingMode.DB, load = true),
            "stm" to BlockTransfer(BlockAddressingMode.IA, load = false),
            "stmia" to BlockTransfer(BlockAddressingMode.IA, load = false),
            "stmib" to BlockTransfer(BlockAddressingMode.IB, load = false),
            "stmda" to BlockTransfer(BlockAddressingMode.DA, load = false),
            "stmdb" to BlockTransfer(BlockAddressingMode.DB, load = false),
            "stmfd" to BlockTransfer(BlockAddressingMode.DB, load = false),
            "stmed" to BlockTransfer(BlockAddressingMode.DA, load = false),
            "stmfa" to BlockTransfer(BlockAddressingMode.IB, load = false),
            "stmea" to BlockTransfer(BlockAddressingMode.IA, load = false),
            "pop" to BlockTransfer(BlockAddressingMode.IA, load = true),
            "push" to BlockTransfer(BlockAddressingMode.DB, load = false),
        )

        fun isBlockTransfer(mnemonic: String): Boolean = mnemonic in blockTransfers
    }

    /**
     * Encodes LDR/STR (word, immediate offset) — ARM A1, bits [27:25] = 010.
     *
     * Syntax: `[Rn, #+/-imm]`, `[Rn, #+/-imm]!`, `[Rn], #+/-imm`
     */
    fun encodeLoadStoreWordImmediate(
        condition: ARMv7InstructionConditionCode,
        L: Boolean,
        P: Boolean,
        U: Boolean,
        W: Boolean,
        Rn: ARMv7Register,
        Rt: ARMv7Register,
        imm12: Int,
    ): Int =
        (condition.code shl 28) or
            (0b010 shl 25) or
            (P.toInt() shl 24) or
            (U.toInt() shl 23) or
            (0 shl 22) or
            (W.toInt() shl 21) or
            (L.toInt() shl 20) or
            (Rn.getIDSafe() shl 16) or
            (Rt.getIDSafe() shl 12) or
            (imm12 and 0xFFF)

    /**
     * Encodes bits [11:0] for LDR/STR (word, register offset) when the offset register is Rm.
     *
     * Layout matches data-processing operand2: optional shift on Rm, then Rm in bits [3:0].
     * When there is no shift, this is equivalent to LSL #0 on Rm.
     */
    private fun encodeShiftedOffsetRegisterBits(
        Rm: ARMv7Register,
        shift: Option<ARMv7InstructionOperand.Register.Shift>,
    ): Int {
        if (shift.isNone()) {
            // No shift on Rm: imm5=0, type=LSL, bit4=0 → plain register offset
            return Rm.getIDSafe()
        }

        val shiftInfo = shift.unwrap()
        return when (val shiftBy = shiftInfo.shiftBy) {
            is ARMv7InstructionOperand.Number -> {
                // Immediate shift amount on Rm (e.g. `lsl #2`)
                val imm5 = ARMv7Immediate.encode5bitImmediate(shiftBy.value.toString(), shiftInfo.shiftType)
                (imm5 shl 7) or (shiftInfo.shiftType.code shl 5) or (0 shl 4) or Rm.getIDSafe()
            }

            is ARMv7InstructionOperand.Register -> {
                throw AssemblySyntaxException(
                    "Register-controlled shifts are not supported for memory register offsets. Use an immediate shift."
                )
            }

            else -> throw AssemblySyntaxException("Invalid shift operand for memory offset register.")
        }
    }

    /**
     * Encodes LDR/STR (word, register offset) — ARM A1, bits [27:25] = 011.
     *
     * Syntax: `[Rn, Rm]`, `[Rn, Rm, <shift>]`, `[Rn, Rm]!`
     */
    fun encodeLoadStoreWordRegister(
        condition: ARMv7InstructionConditionCode,
        L: Boolean,
        P: Boolean,
        U: Boolean,
        W: Boolean,
        Rn: ARMv7Register,
        Rt: ARMv7Register,
        Rm: ARMv7Register,
        shift: Option<ARMv7InstructionOperand.Register.Shift>,
    ): Int {
        val offsetRegisterBits = encodeShiftedOffsetRegisterBits(Rm, shift)
        return (condition.code shl 28) or
            (0b011 shl 25) or
            (P.toInt() shl 24) or
            (U.toInt() shl 23) or
            (0 shl 22) or
            (W.toInt() shl 21) or
            (L.toInt() shl 20) or
            (Rn.getIDSafe() shl 16) or
            (Rt.getIDSafe() shl 12) or
            offsetRegisterBits
    }

    private fun encodeNumericalOffset(
        instruction: ARMv7InstructionMixin,
        Rt: ARMv7InstructionOperand.Register,
        Rn: ARMv7Register,
        amount: Int,
        flags: ARMv7InstructionOperand.AddressingFlags,
    ): Int {
        val (imm12, _) = ARMv7Immediate.encodeNumericalOffset(amount)
        val isLoad = getOpcode(instruction.baseMnemonic) == 1

        return encodeLoadStoreWordImmediate(
            condition = instruction.conditionCode,
            L = isLoad,
            P = flags.preIndexed,
            U = flags.add,
            // W=1 only for pre-index with writeback (!). Post-index is P=0,W=0 (P=0,W=1 is LDRT/STRT).
            W = flags.preIndexed && flags.writeBack,
            Rn = Rn,
            Rt = Rt.register,
            imm12 = imm12,
        )
    }

    private fun encodeRegisterOffset(
        instruction: ARMv7InstructionMixin,
        Rt: ARMv7InstructionOperand.Register,
        Rn: ARMv7Register,
        index: ARMv7InstructionOperand.Register,
        flags: ARMv7InstructionOperand.AddressingFlags,
    ): Int {
        val isLoad = getOpcode(instruction.baseMnemonic) == 1

        return encodeLoadStoreWordRegister(
            condition = instruction.conditionCode,
            L = isLoad,
            P = flags.preIndexed,
            U = flags.add,
            // W=1 only for pre-index with writeback (!). Post-index is P=0,W=0 (P=0,W=1 is LDRT/STRT).
            W = flags.preIndexed && flags.writeBack,
            Rn = Rn,
            Rt = Rt.register,
            Rm = index.register,
            shift = index.shift,
        )
    }

    private fun encodeLoadStore(
        instruction: ARMv7InstructionMixin,
        operands: List<ARMv7Operand>,
    ): Int {
        if (operands.size != 2) {
            throw AssemblySyntaxException(
                "Invalid syntax for ${instruction.baseMnemonic}, expected 2 operands, received ${operands.size}."
            )
        }

        val rt = operands[0].operand as? ARMv7InstructionOperand.Register
            ?: throw AssemblySyntaxException(
                "${instruction.baseMnemonic.uppercase()} requires a register as the first operand."
            )

        if (rt.shift.isSome()) {
            throw AssemblySyntaxException("Shift operators are not permitted on the transfer register operand.")
        }

        val address = operands[1].operand as? ARMv7InstructionOperand.RegisterWithOffset
            ?: throw AssemblySyntaxException(
                "Invalid memory operand for ${instruction.baseMnemonic}."
            )

        return when (val offset = address.offset) {
            // immediate LDR/STR
            is ARMv7InstructionOperand.Offset.NumericalOffset ->
                encodeNumericalOffset(instruction, rt, address.register, offset.amount, address.flags)

            // register LDR/STR
            is ARMv7InstructionOperand.Offset.RegisterOffset ->
                encodeRegisterOffset(instruction, rt, address.register, offset.register, address.flags)
        }
    }

    /**
     * Encodes LDM/STM (and PUSH/POP) — ARM A1, bits [27:25] = 100.
     *
     * The S bit (bit 22, the `^` suffix) is always 0. Registers are selected by a 16-bit mask in bits [15:0].
     * Ignore carrot syntax for now: it is only used for return with exceptions or priveleged instructions.
     */
    fun encodeBlockTransfer(
        condition: ARMv7InstructionConditionCode,
        L: Boolean,
        P: Boolean,
        U: Boolean,
        W: Boolean,
        Rn: ARMv7Register,
        registerMask: Int,
    ): Int =
        (condition.code shl 28) or
            (0b100 shl 25) or
            (P.toInt() shl 24) or
            (U.toInt() shl 23) or
            (0 shl 22) or
            (W.toInt() shl 21) or
            (L.toInt() shl 20) or
            (Rn.getIDSafe() shl 16) or
            (registerMask and 0xFFFF)

    /**
     * Syntax: `LDM<mode> Rn{!}, {reglist}`, `STM<mode> Rn{!}, {reglist}`, `PUSH {reglist}`, `POP {reglist}`.
     *
     * PUSH is STMDB SP!, and POP is LDMIA SP!.
     */
    private fun encodeBlockTransferInstruction(
        instruction: ARMv7InstructionMixin,
        transfer: BlockTransfer,
        operands: List<ARMv7Operand>,
    ): Int {
        val name = instruction.baseMnemonic.uppercase()
        val isStack = instruction.baseMnemonic in stackMnemonics
        val expectedOperands = if (isStack) 1 else 2

        if (operands.size != expectedOperands) {
            throw AssemblySyntaxException(
                "Invalid syntax for $name, expected $expectedOperands operands, received ${operands.size}."
            )
        }

        val registerList = operands.last().operand as? ARMv7InstructionOperand.RegisterList
            ?: throw AssemblySyntaxException("$name requires a register list, for example `{r0, r1}`.")

        if (registerList.registers.isEmpty()) {
            throw AssemblySyntaxException("$name register list must contain at least one register.")
        }

        val (baseRegister, writeBack) = if (isStack) {
            ARMv7Register.SP to true
        } else {
            val base = operands[0].operand as? ARMv7InstructionOperand.Register
                ?: throw AssemblySyntaxException("$name requires a base register as the first operand.")

            if (base.shift.isSome()) {
                throw AssemblySyntaxException("Shift operators are not permitted on the base register of $name.")
            }

            base.register to base.writeBack
        }

        if (baseRegister == ARMv7Register.PC) {
            throw AssemblySyntaxException("PC cannot be used as the base register of $name.")
        }

        // Build the mask from register numbers; use the standard order based on ID
        val registerMask = registerList.registers.fold(0) { mask, register -> mask or (1 shl register.getIDSafe()) }

        return encodeBlockTransfer(
            condition = instruction.conditionCode,
            L = transfer.load,
            P = transfer.mode.preIndexed,
            U = transfer.mode.add,
            W = writeBack,
            Rn = baseRegister,
            registerMask = registerMask,
        )
    }

    override fun encode(
        instruction: ARMv7InstructionMixin,
        operands: List<ARMv7Operand>,
        addrCounter: Int,
    ): List<Int> {
        blockTransfers[instruction.baseMnemonic]?.let { transfer ->
            return listOf(encodeBlockTransferInstruction(instruction, transfer, operands))
        }

        if (instruction.baseMnemonic !in loadStoreMnemonics) {
            throw AssemblySyntaxException(
                "Mnemonic ${instruction.baseMnemonic} is not supported by the memory access encoder."
            )
        }

        return listOf(encodeLoadStore(instruction, operands))
    }
}
