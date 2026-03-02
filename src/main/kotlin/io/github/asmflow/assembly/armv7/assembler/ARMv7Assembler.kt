package io.github.asmflow.assembly.armv7.assembler

import com.intellij.psi.PsiFile
import io.github.asmflow.assembly.armv7.psi.ARMv7Instruction
import io.github.asmflow.assembly.assembler.*
import io.github.asmflow.assembly.util.functional.Err
import io.github.asmflow.assembly.util.functional.resultOfException

class ARMv7Assembler : Assembler {
    fun encodeInstruction(instruction: ARMv7Instruction): AssemblerResult<Int, AssemblerError> {
        // TODO: make sure the instruction actually takes operands before returning an error
        val operands =
            instruction.operands ?: return Err(
                AssemblerError(
                    "Instruction ${instruction.text} has no operands.",
                    instruction
                )
            )

        return try {
            resultOfException { ARMv7DataProcessingEncoder.encode(instruction, operands.operandList.requireNoNulls()) }
                .mapErr { AssemblerError(it.message.orEmpty(), instruction) }
        } catch (_: IllegalArgumentException) {
            throw AssemblySyntaxException("This should not happen")
        }
    }

    override fun assemble(files: List<PsiFile>): AssemblerResult<List<AssembledInstruction>, List<AssemblerError>> {
        val file = files[0] // For now support one file
        val errors = mutableListOf<AssemblerError>()

        // ROUND 2: Convert all the instructions into bytecode
        for (child in file.children) {
            if (child is ARMv7Instruction) {
                val result = encodeInstruction(child)
                if (result.isErr())
                    errors.add(result.unwrapErr())
            }
        }

        return Err(errors)
        // TODO: return the successfully compiled version
    }
}
