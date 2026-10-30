package io.github.asmflow.assembly.armv7.assembler.encoder

import io.github.asmflow.assembly.armv7.assembler.AssemblySyntaxException
import io.github.asmflow.assembly.armv7.assembler.utils.ARMv7Immediate
import io.github.asmflow.assembly.armv7.execution.ARMv7InstructionConditionCode
import io.github.asmflow.assembly.armv7.execution.ARMv7InstructionOperand
import io.github.asmflow.assembly.armv7.execution.ARMv7Register
import io.github.asmflow.assembly.armv7.psi.ARMv7InstructionMixin
import io.github.asmflow.assembly.armv7.psi.ARMv7Operand
import io.github.asmflow.assembly.armv7.util.functional.Option

class ARMv7MemoryAccessEncoder(val symbols: Map<String, UInt>) : ARMv7InstructionEncoder {
    private fun Boolean.toInt() = if (this) 1 else 0

    private enum class BlockAddressingMode(val preIndexed: Boolean, val add: Boolean) {
        IA(preIndexed = false, add = true),
        IB(preIndexed = true, add = true),
        DA(preIndexed = false, add = false),
        DB(preIndexed = true, add = false),
    }

    private data class BlockTransfer(val mode: BlockAddressingMode, val load: Boolean)

    // LDR / STR, word or byte: bits [27:26] = 01, B (bit 22) selects a byte transfer
    private data class WordOrByteTransfer(val load: Boolean, val byte: Boolean)

    // LDRH / STRH / LDRSB / LDRSH ("extra" load/store): bits [27:25] = 000, bits [7:4] = 1 op 1
    private data class ExtraTransfer(val load: Boolean, val op: Int)

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

        private val wordOrByteTransfers = mapOf(
            "ldr" to WordOrByteTransfer(load = true, byte = false),
            "str" to WordOrByteTransfer(load = false, byte = false),
            "ldrb" to WordOrByteTransfer(load = true, byte = true),
            "strb" to WordOrByteTransfer(load = false, byte = true),
        )

        private val extraTransfers = mapOf(
            "ldrh" to ExtraTransfer(load = true, op = 0b01),
            "strh" to ExtraTransfer(load = false, op = 0b01),
            "ldrsb" to ExtraTransfer(load = true, op = 0b10),
            "ldrsh" to ExtraTransfer(load = true, op = 0b11),
        )

        fun isBlockTransfer(mnemonic: String): Boolean = mnemonic in blockTransfers
    }

    /**
     * Encodes LDR/STR/LDRB/STRB (immediate offset) — ARM A1, bits [27:25] = 010.
     *
     * Syntax: `[Rn, #+/-imm]`, `[Rn, #+/-imm]!`, `[Rn], #+/-imm`
     */
    fun encodeLoadStoreWordImmediate(
        condition: ARMv7InstructionConditionCode,
        L: Boolean,
        B: Boolean,
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
            (B.toInt() shl 22) or
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
     * Encodes LDR/STR/LDRB/STRB (register offset) — ARM A1, bits [27:25] = 011.
     *
     * Syntax: `[Rn, Rm]`, `[Rn, Rm, <shift>]`, `[Rn, Rm]!`
     */
    fun encodeLoadStoreWordRegister(
        condition: ARMv7InstructionConditionCode,
        L: Boolean,
        B: Boolean,
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
            (B.toInt() shl 22) or
            (W.toInt() shl 21) or
            (L.toInt() shl 20) or
            (Rn.getIDSafe() shl 16) or
            (Rt.getIDSafe() shl 12) or
            offsetRegisterBits
    }

    private fun encodeNumericalOffset(
        instruction: ARMv7InstructionMixin,
        transfer: WordOrByteTransfer,
        Rt: ARMv7InstructionOperand.Register,
        Rn: ARMv7Register,
        amount: Int,
        flags: ARMv7InstructionOperand.AddressingFlags,
    ): Int {
        val (imm12, _) = ARMv7Immediate.encodeNumericalOffset(amount)

        return encodeLoadStoreWordImmediate(
            condition = instruction.conditionCode,
            L = transfer.load,
            B = transfer.byte,
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
        transfer: WordOrByteTransfer,
        Rt: ARMv7InstructionOperand.Register,
        Rn: ARMv7Register,
        index: ARMv7InstructionOperand.Register,
        flags: ARMv7InstructionOperand.AddressingFlags,
    ): Int {
        return encodeLoadStoreWordRegister(
            condition = instruction.conditionCode,
            L = transfer.load,
            B = transfer.byte,
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

    /**
     * Checks the operands of a single load/store and returns the transfer register and the address.
     */
    private fun parseLoadStoreOperands(
        instruction: ARMv7InstructionMixin,
        operands: List<ARMv7Operand>,
    ): Pair<ARMv7InstructionOperand.Register, ARMv7InstructionOperand.RegisterWithOffset> {
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

        return rt to address
    }

    private fun encodeWordOrByteTransfer(
        instruction: ARMv7InstructionMixin,
        transfer: WordOrByteTransfer,
        operands: List<ARMv7Operand>,
    ): Int {
        val (rt, address) = parseLoadStoreOperands(instruction, operands)

        return when (val offset = address.offset) {
            // immediate LDR/STR
            is ARMv7InstructionOperand.Offset.NumericalOffset ->
                encodeNumericalOffset(instruction, transfer, rt, address.register, offset.amount, address.flags)

            // register LDR/STR
            is ARMv7InstructionOperand.Offset.RegisterOffset ->
                encodeRegisterOffset(instruction, transfer, rt, address.register, offset.register, address.flags)
        }
    }

    /**
     * Encodes LDRH/STRH/LDRSB/LDRSH (immediate offset) — ARM A1, bits [27:25] = 000, bit 22 = 1.
     *
     * The offset is 8 bits, split into imm4H (bits [11:8]) and imm4L (bits [3:0]).
     */
    fun encodeExtraLoadStoreImmediate(
        condition: ARMv7InstructionConditionCode,
        L: Boolean,
        P: Boolean,
        U: Boolean,
        W: Boolean,
        Rn: ARMv7Register,
        Rt: ARMv7Register,
        op: Int,
        imm8: Int,
    ): Int =
        (condition.code shl 28) or
            (0b000 shl 25) or
            (P.toInt() shl 24) or
            (U.toInt() shl 23) or
            (1 shl 22) or
            (W.toInt() shl 21) or
            (L.toInt() shl 20) or
            (Rn.getIDSafe() shl 16) or
            (Rt.getIDSafe() shl 12) or
            (((imm8 ushr 4) and 0xF) shl 8) or
            (1 shl 7) or
            (op shl 5) or
            (1 shl 4) or
            (imm8 and 0xF)

    /**
     * Encodes LDRH/STRH/LDRSB/LDRSH (register offset) — ARM A1, bits [27:25] = 000, bit 22 = 0.
     *
     * Unlike LDR/STR, the offset register cannot be shifted.
     */
    fun encodeExtraLoadStoreRegister(
        condition: ARMv7InstructionConditionCode,
        L: Boolean,
        P: Boolean,
        U: Boolean,
        W: Boolean,
        Rn: ARMv7Register,
        Rt: ARMv7Register,
        op: Int,
        Rm: ARMv7Register,
    ): Int =
        (condition.code shl 28) or
            (0b000 shl 25) or
            (P.toInt() shl 24) or
            (U.toInt() shl 23) or
            (0 shl 22) or
            (W.toInt() shl 21) or
            (L.toInt() shl 20) or
            (Rn.getIDSafe() shl 16) or
            (Rt.getIDSafe() shl 12) or
            (0b0000 shl 8) or
            (1 shl 7) or
            (op shl 5) or
            (1 shl 4) or
            Rm.getIDSafe()

    private fun encodeExtraTransfer(
        instruction: ARMv7InstructionMixin,
        transfer: ExtraTransfer,
        operands: List<ARMv7Operand>,
    ): Int {
        val name = instruction.baseMnemonic.uppercase()
        val (rt, address) = parseLoadStoreOperands(instruction, operands)
        val flags = address.flags
        // W=1 only for pre-index with writeback (!). Post-index is P=0,W=0 (P=0,W=1 is LDRHT/STRHT).
        val writeBack = flags.preIndexed && flags.writeBack

        return when (val offset = address.offset) {
            is ARMv7InstructionOperand.Offset.NumericalOffset -> {
                if (offset.amount !in 0..255) {
                    throw AssemblySyntaxException("Offset ${offset.amount} out of range for $name (must be 0-255).")
                }
                encodeExtraLoadStoreImmediate(
                    condition = instruction.conditionCode,
                    L = transfer.load,
                    P = flags.preIndexed,
                    U = flags.add,
                    W = writeBack,
                    Rn = address.register,
                    Rt = rt.register,
                    op = transfer.op,
                    imm8 = offset.amount,
                )
            }

            is ARMv7InstructionOperand.Offset.RegisterOffset -> {
                if (offset.register.shift.isSome()) {
                    throw AssemblySyntaxException("$name register offsets cannot be shifted.")
                }
                encodeExtraLoadStoreRegister(
                    condition = instruction.conditionCode,
                    L = transfer.load,
                    P = flags.preIndexed,
                    U = flags.add,
                    W = writeBack,
                    Rn = address.register,
                    Rt = rt.register,
                    op = transfer.op,
                    Rm = offset.register.register,
                )
            }
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

        wordOrByteTransfers[instruction.baseMnemonic]?.let { transfer ->
            return listOf(encodeWordOrByteTransfer(instruction, transfer, operands))
        }

        extraTransfers[instruction.baseMnemonic]?.let { transfer ->
            return listOf(encodeExtraTransfer(instruction, transfer, operands))
        }

        throw AssemblySyntaxException(
            "Mnemonic ${instruction.baseMnemonic} is not supported by the memory access encoder."
        )
    }
}
