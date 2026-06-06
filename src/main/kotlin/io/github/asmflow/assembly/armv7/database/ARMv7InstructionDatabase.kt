package io.github.asmflow.assembly.armv7.database

import io.github.asmflow.assembly.armv7.database.InstructionFormat.Companion.toInstructionFormat
import io.github.asmflow.assembly.openapi.BundledXmlDatabase
import io.github.asmflow.assembly.util.functional.Option
import io.github.asmflow.assembly.util.functional.toOption
import org.w3c.dom.Document
import org.w3c.dom.Element

enum class InstructionFormat(val instructionClass: Int?, val humanReadble: String) {
    DATA_PROCESSING(0b00, "DataProcessing"),
    MULTIPLY(0b00, "Multiply"),
    MEMORY_ACCESS(0b01, "MemoryAccess"),
    BRANCH(0b10, "Branch"),
    PSUEDO(null, "Psuedo");

    companion object {
        fun String.toInstructionFormat() = entries.find { it.humanReadble == this }
    }
}

object ARMv7InstructionDatabase :
    BundledXmlDatabase<String, ARMv7InstructionDatabase.Instruction>("/armv7/InstructionDatabase.xml") {
    data class Instruction(
        val mnemonic: String,
        val supportsFlags: Boolean,
        val supportsConditionCodes: Boolean,
        val details: InstructionDetails,
        val format: InstructionFormat,
        val opcode: UInt?

    )

    data class InstructionDetails(
        val shortDescription: String,
        val variantList: List<InstructionVariant>
    )

    data object InstructionVariant

    override fun parseDocument(document: Document): Map<String, Instruction> {
        val instructions = document.getElementsByTagName("instruction")

        return buildMap {
            for (i in 0 until instructions.length) {
                val element = instructions.item(i) as? Element ?: continue

                val mnemonic = element.getAttribute("name")
                val supportsFlags = element.getAttribute("supportsFlags").toBooleanStrictOrNull() ?: false
                val supportsConditionCodes =
                    element.getAttribute("supportsConditionCodes").toBooleanStrictOrNull() ?: false
                val instructionFormat = element.getAttribute("format").toInstructionFormat() ?: InstructionFormat.PSUEDO
                val opcode = element.getAttribute("opcode").toUIntOrNull(2)

                val elements = element.getElementsByTagName("shortDescription")
                val shortDescriptionElement = elements.item(0) as? Element
                val shortDescription = shortDescriptionElement?.textContent ?: ""

                put(
                    mnemonic,
                    Instruction(
                        mnemonic,
                        supportsFlags,
                        supportsConditionCodes,
                        InstructionDetails(shortDescription, emptyList()),
                        instructionFormat,
                        opcode
                    )
                )
            }
        }
    }

    fun allInstructions(): List<Instruction> = data.values.toList()

    fun get(mnemonic: String): Option<Instruction> = data[mnemonic].toOption()

    fun getOpcode(mnemonic: String): Int {
        return data[mnemonic]?.opcode?.toInt() ?: throw Exception("Database incomplete for mnemonic $mnemonic")
    }

    fun findByOpcode(
        opcode: Int,
        format: InstructionFormat? = null,
        supportsFlags: Boolean? = null,
        supportsConditionCodes: Boolean? = null,
        additionalFilter: (Instruction) -> Boolean = { true }
    ): List<Instruction> {
        val opcodeUInt = opcode.toUInt()
        return data.values.filter { instruction ->
            instruction.opcode == opcodeUInt &&
                    (format == null || instruction.format == format) &&
                    (supportsFlags == null || instruction.supportsFlags == supportsFlags) &&
                    (supportsConditionCodes == null || instruction.supportsConditionCodes == supportsConditionCodes) &&
                    additionalFilter(instruction)
        }
    }

    fun getByOpcode(
        opcode: Int,
        format: InstructionFormat? = null,
        supportsFlags: Boolean? = null,
        supportsConditionCodes: Boolean? = null,
        additionalFilter: (Instruction) -> Boolean = { true }
    ): Option<Instruction> {
        return findByOpcode(
            opcode = opcode,
            format = format,
            supportsFlags = supportsFlags,
            supportsConditionCodes = supportsConditionCodes,
            additionalFilter = additionalFilter
        ).firstOrNull().toOption()
    }
}
