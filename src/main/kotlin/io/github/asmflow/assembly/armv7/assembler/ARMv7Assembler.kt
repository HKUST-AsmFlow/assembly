package io.github.asmflow.assembly.armv7.assembler

import com.intellij.psi.PsiFile
import io.github.asmflow.assembly.assembler.Assembler
import io.github.asmflow.assembly.assembler.AssemblerResult
import io.github.asmflow.assembly.armv7.psi.ARMv7Instruction
import io.github.asmflow.assembly.armv7.psi.ARMv7InstructionMixin
import io.github.asmflow.assembly.assembler.AssemblerError
import io.github.asmflow.assembly.assembler.AssemblySyntaxException

class ARMv7Assembler: Assembler {
    fun encodeInstruction(instruction: ARMv7Instruction): Int {
        val operands = instruction.operands ?: throw AssemblySyntaxException("Instruction ${instruction.text} has no operands.")
        try{
            return Armv7DataProcessingEncoder.encode(instruction, operands.operandList.requireNoNulls())
        }
        catch(e: IllegalArgumentException) {
            throw AssemblySyntaxException("This should not happen")
        }
    }
    override fun assemble(files: List<PsiFile>): AssemblerResult {
        val file = files[0] // For now support one file
        val errors = mutableListOf<AssemblerError>()

        // ROUND 2: Convert all the instructions into bytecode
        for (child in file.children) {
            if (child is ARMv7Instruction){
                try{encodeInstruction(child)}
                //encodeInstruction(child)
                catch(e: AssemblySyntaxException){
                    val err = AssemblerError(e.message.orEmpty(), child)
                    errors.add(err)
                }
            }
        }

        return AssemblerResult.Failure(errors)
        // TODO: return the successfully compiled version

    }

}
