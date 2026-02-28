package io.github.asmflow.assembly.armv7.database

import com.intellij.util.containers.MultiMap
import io.github.asmflow.assembly.openapi.BundledXmlDatabase
import org.jdom.Element

class ARMv7InstructionDatabase :
    BundledXmlDatabase<String, ARMv7InstructionDatabase.Instruction>("/armv7/InstructionDatabase.xml") {
    data class Instruction(val mnemonic: String)

    override fun parseElement(root: Element): MultiMap<String, Instruction> = MultiMap.create()
}
