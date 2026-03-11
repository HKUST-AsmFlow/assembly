package io.github.asmflow.assembly.editorActions

sealed class AssemblyCompletionContext {
    open val inInstruction: Boolean = false

    object General : AssemblyCompletionContext() {
        override val inInstruction: Boolean = true
    }
}
