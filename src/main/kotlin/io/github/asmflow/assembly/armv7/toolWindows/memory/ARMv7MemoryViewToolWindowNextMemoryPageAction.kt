package io.github.asmflow.assembly.armv7.toolWindows.memory

import com.intellij.icons.AllIcons
import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class ARMv7MemoryViewToolWindowNextMemoryPageAction(private val model: ARMv7MemoryViewTableModel) : AnAction(
    "Next Memory Page",
    "Show the next memory page",
    AllIcons.Actions.Forward
) {
    override fun actionPerformed(e: AnActionEvent) {
        model.movePage(1)
    }

    override fun getActionUpdateThread() = ActionUpdateThread.EDT

    override fun update(e: AnActionEvent) {
        e.presentation.isEnabled = model.canMovePage(1)
    }
}
