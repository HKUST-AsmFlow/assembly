package io.github.asmflow.assembly.armv7.assembler

import com.intellij.psi.util.elementType
import io.github.asmflow.assembly.armv7.ARMv7File
import io.github.asmflow.assembly.armv7.psi.ARMv7AdcInstruction
import io.github.asmflow.assembly.armv7.psi.ARMv7Label
import io.github.asmflow.assembly.armv7.psi.ARMv7TokenTypes
import io.github.asmflow.assembly.armv7.psi.IARMv7InstructionSuffix

class Armv7Assembler(var file: ARMv7File) {
    fun assemble() {
        val symbols = HashMap<String, Int>()
        var instructionCounter = 0
        for (child in file.children) {
            if (child.elementType == ARMv7TokenTypes.LABEL) {
                // (child as ARMv7Label).
                // some way to get the label name without using PsiElement.getText()
                val labelName = child.text.drop(1)
                symbols[labelName] = instructionCounter
            }
            if (child is IARMv7InstructionSuffix) {
                instructionCounter += 4 // Each instruction is 4 bytes ignoring Thumb state
            }
        }
    }
}