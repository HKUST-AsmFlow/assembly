package io.github.asmflow.assembly.armv7.assembler.context

class AssemblerContext {
    var section: ProgramSection = ProgramSection.Text
    var dataOffsetBytes = 0
    var textOffsetWords = 0

    val symbols = HashMap<String, ProgramSymbol>()

    val text = mutableListOf<Int>()
    val data = mutableListOf<Byte>()

    fun advanceData(bytes: Int) {
        dataOffsetBytes += bytes
    }

    fun advanceText(words: Int) {
        textOffsetWords += words
    }

    fun currentOffset(): Int = when (section) {
        ProgramSection.Data -> dataOffsetBytes
        ProgramSection.Text -> textOffsetWords
    }

    fun reset() {
        section = ProgramSection.Text
        dataOffsetBytes = 0
        textOffsetWords = 0
    }
}
