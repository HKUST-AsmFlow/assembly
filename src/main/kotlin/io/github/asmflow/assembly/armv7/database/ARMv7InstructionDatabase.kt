package io.github.asmflow.assembly.armv7.database

import io.github.asmflow.assembly.armv7.psi.ARMv7Instruction
import io.github.asmflow.assembly.openapi.BundledXmlDatabase
import org.jdom.Document

object ARMv7InstructionDatabase :
    BundledXmlDatabase<String, ARMv7InstructionDatabase.Instruction>("/armv7/InstructionDatabase.xml") {
    data class Instruction(val mnemonic: String)

    override fun parseDocument(document: Document): Map<String, Instruction> {
        require(document.rootElement.name == "instructions")

        val root = document.rootElement
        return root.getChildren("instruction").associate {
            val mnemonic = it.getAttribute("name").value
            mnemonic to Instruction(mnemonic)
        }
    }

    fun any(instruction: ARMv7Instruction): Boolean = data.any { instruction.mnemonic.textMatches(it.key) }
}
