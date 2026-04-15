package io.github.asmflow.assembly.armv7.toolWindows

import com.intellij.ui.dsl.builder.panel
import javax.swing.JPanel

object ARMv7RegisterViewToolWindow {
    fun makeContent(): JPanel = panel {
        row {
            label("R0")
        }
        row {
            label("R1")
        }
        row {
            label("R2")
        }
        row {
            label("R3")
        }
        row {
            label("R4")
        }
        row {
            label("R5")
        }
        row {
            label("R6")
        }
        row {
            label("R7")
        }
        row {
            label("R8")
        }
        row {
            label("R9")
        }
        row {
            label("R10")
        }
        row {
            label("R12")
        }
        row {
            label("FP")
        }
        row {
            label("SP")
        }
        row {
            label("LR")
        }
        row {
            label("PC")
        }
    }
}
