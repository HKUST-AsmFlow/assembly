package io.github.asmflow.assembly.armv7.assembler.psuedo

import io.github.asmflow.assembly.assembler.AssemblySyntaxException

object PsuedoEncoderFactory {
    fun getEncoder(mnemonic: String): ARMv7PsuedoEncoder {
        return when (mnemonic.lowercase()) {
            "adr" -> AdrEncoder
            "adrl" -> AdrlEncoder
            "ldr" -> LdrPsuedoEncoder
            else -> throw AssemblySyntaxException("No psuedo-encoder implemented for $mnemonic")
        }
    }
}