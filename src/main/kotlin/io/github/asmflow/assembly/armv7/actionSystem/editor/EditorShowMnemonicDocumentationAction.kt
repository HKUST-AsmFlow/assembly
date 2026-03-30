package io.github.asmflow.assembly.armv7.actionSystem.editor

import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.wm.ToolWindowManager
import com.intellij.psi.util.PsiTreeUtil
import io.github.asmflow.assembly.armv7.database.ARMv7InstructionDatabase
import io.github.asmflow.assembly.armv7.database.ARMv7InstructionDatabase.Instruction
import io.github.asmflow.assembly.armv7.psi.ARMv7Mnemonic
import io.github.asmflow.assembly.armv7.toolWindows.ARMv7MnemonicDocumentationToolWindowFactory
import io.github.asmflow.assembly.util.functional.toOption

class EditorShowMnemonicDocumentationAction : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        val project = event.project.toOption()
        if (project.isNone())
            return

        val toolWindow = ToolWindowManager.getInstance(project.unwrap()).getToolWindow(
            ARMv7MnemonicDocumentationToolWindowFactory.Companion.toolWindowId
        ).toOption()

        toolWindow.isSomeThen { it.show() }
    }

    override fun getActionUpdateThread(): ActionUpdateThread = ActionUpdateThread.BGT

    override fun update(event: AnActionEvent) {
        val project = event.project.toOption()
        val editor = event.getData(CommonDataKeys.EDITOR).toOption()
        val file = event.getData(CommonDataKeys.PSI_FILE).toOption()

        if (project.isNone() || editor.isNone() || file.isNone()) {
            event.presentation.isEnabledAndVisible = false
            return
        }

        val offset = editor.unwrap().caretModel.offset
        val elementAtCaret = file.unwrap().findElementAt(offset).toOption()

        if (elementAtCaret.isNone()) {
            event.presentation.isEnabledAndVisible = false
            return
        }

        val mnemonicElement = PsiTreeUtil.getParentOfType(elementAtCaret.unwrap(), ARMv7Mnemonic::class.java).toOption()
        event.presentation.isEnabledAndVisible = mnemonicElement.isSome()

        mnemonicElement.isSomeThen {
            val inst = ARMv7InstructionDatabase.get(it.text)
            if (inst.isNone())
                return@isSomeThen
        }
    }
}
