package io.github.asmflow.assembly.editorActions

sealed class AssemblyCompletionContext {
    open val inInstruction: Boolean = false
}
