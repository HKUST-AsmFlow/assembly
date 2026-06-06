package io.github.asmflow.assembly.debugger.evaluation

import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.EditorFactory
import com.intellij.openapi.fileTypes.FileType
import com.intellij.openapi.project.Project
import com.intellij.xdebugger.XExpression
import com.intellij.xdebugger.XSourcePosition
import com.intellij.xdebugger.evaluation.EvaluationMode
import com.intellij.xdebugger.evaluation.XDebuggerEditorsProvider
import io.github.asmflow.assembly.armv7.ARMv7FileType

class AssemblyDebugEditorsProvider : XDebuggerEditorsProvider() {
    override fun getFileType(): FileType = ARMv7FileType

    override fun createDocument(
        project: Project,
        expression: XExpression,
        sourcePosition: XSourcePosition?,
        mode: EvaluationMode
    ): Document = EditorFactory.getInstance().createDocument(expression.expression)
}
