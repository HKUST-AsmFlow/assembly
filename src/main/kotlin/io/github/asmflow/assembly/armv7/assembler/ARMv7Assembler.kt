package io.github.asmflow.assembly.armv7.assembler

import io.github.asmflow.assembly.armv7.ARMv7File
import io.github.asmflow.assembly.armv7.psi.ARMv7Label
import io.github.asmflow.assembly.armv7.psi.IARMv7InstructionSuffix

class ARMv7Assembler(var file: ARMv7File) {
    fun assemble() {
        val symbols = HashMap<String, Int>()
        var instructionCounter = 0
        for (child in file.children) {
            if (child is ARMv7Label) {
                val labelName = child.getLabelName()
                symbols[labelName] = instructionCounter
            }

            if (child is IARMv7InstructionSuffix) {
                instructionCounter += 4 // Each instruction is 4 bytes ignoring Thumb state
            }
        }
    }
}
