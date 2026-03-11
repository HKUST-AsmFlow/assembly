package io.github.asmflow.assembly.armv7.database

import io.github.asmflow.assembly.armv7.psi.ARMv7Instruction
import io.github.asmflow.assembly.openapi.BundledXmlDatabase
import io.github.asmflow.assembly.util.functional.Option
import io.github.asmflow.assembly.util.functional.toOption
import org.w3c.dom.Document
import org.w3c.dom.Element

object ARMv7InstructionDatabase :
    BundledXmlDatabase<String, ARMv7InstructionDatabase.Instruction>("/armv7/InstructionDatabase.xml") {
    data class Instruction(
        val mnemonic: String,
        val supportsFlags: Boolean,
        val supportsConditionCodes: Boolean,
        val details: InstructionDetails
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

                val elements = element.getElementsByTagName("shortDescription")
                val shortDescriptionElement = elements.item(0) as? Element
                val shortDescription = shortDescriptionElement?.textContent ?: ""

                put(
                    mnemonic,
                    Instruction(
                        mnemonic,
                        supportsFlags,
                        supportsConditionCodes,
                        InstructionDetails(shortDescription, emptyList())
                    )
                )
            }
        }
    }

    fun allInstructions(): List<Instruction> = data.values.toList()

    operator fun get(instruction: ARMv7Instruction): Option<Instruction> = data[instruction.baseMnemonic].toOption()
}
