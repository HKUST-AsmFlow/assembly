package io.github.asmflow.assembly.armv7.toolWindows

import com.intellij.ui.dsl.builder.panel
import io.github.asmflow.assembly.armv7.emulator.ARMv7MemoryState
import javax.swing.JPanel

class ARMv7MemoryViewToolWindow {
    fun getContent(): JPanel = panel {  }

    fun updateState(state: ARMv7MemoryState) {}
}
