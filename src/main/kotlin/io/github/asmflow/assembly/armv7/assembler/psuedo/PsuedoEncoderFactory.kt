package io.github.asmflow.assembly.armv7.assembler.psuedo

import io.github.asmflow.assembly.assembler.AssemblySyntaxException

/**
 * Factory for converting mnemonics to their corresponding encoders.
 *
 * Currently, this is implemented for:
 * - adr
 * - adrl
 * - ldr
 */
object PsuedoEncoderFactory {
    fun getEncoder(mnemonic: String, symbols: HashMap<String, Int>): ARMv7PsuedoEncoder {
        return when (mnemonic.lowercase()) {
            "adr" -> AdrEncoder(symbols)
            "adrl" -> AdrlEncoder(symbols)
            "ldr" -> LdrPsuedoEncoder
            else -> throw AssemblySyntaxException("No psuedo-encoder implemented for $mnemonic")
        }
    }
}