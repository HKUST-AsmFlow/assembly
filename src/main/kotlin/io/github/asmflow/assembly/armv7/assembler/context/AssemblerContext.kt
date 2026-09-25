package io.github.asmflow.assembly.armv7.assembler.context

class AssemblerContext {
    var section: Section = Section.Text
    var dataOffsetBytes = 0
    var textOffsetWords = 0

    val symbols = HashMap<String, Symbol>()

    val text = mutableListOf<Int>()
    val data = mutableListOf<Byte>()

    fun advanceData(bytes: Int) {
        dataOffsetBytes += bytes
    }

    fun advanceText(words: Int) {
        textOffsetWords += words
    }

    fun currentOffset(): Int = when (section) {
        Section.Data -> dataOffsetBytes
        Section.Text -> textOffsetWords
    }

    data class Symbol(val section: Section, val offset: Int)

    enum class Section {
        Data,
        Text,
    }
}
