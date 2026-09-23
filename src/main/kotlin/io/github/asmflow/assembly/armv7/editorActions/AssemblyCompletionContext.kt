package io.github.asmflow.assembly.armv7.editorActions

sealed class AssemblyCompletionContext {
    open val inInstruction: Boolean = false

    object General : AssemblyCompletionContext() {
        override val inInstruction: Boolean = true
    }
}
