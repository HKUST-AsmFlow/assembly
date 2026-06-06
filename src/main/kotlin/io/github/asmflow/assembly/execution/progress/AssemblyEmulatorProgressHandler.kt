package io.github.asmflow.assembly.execution.progress

import com.intellij.execution.process.ProcessHandler
import com.intellij.execution.process.ProcessOutputTypes
import com.intellij.openapi.application.ApplicationManager
import java.io.OutputStream

class AssemblyEmulatorProgressHandler(
    private val runEmulation: AssemblyEmulatorProgressHandler.() -> Unit
) : ProcessHandler() {
    override fun detachIsDefault(): Boolean = true

    override fun getProcessInput(): OutputStream? = null

    override fun destroyProcessImpl() {
        notifyProcessTerminated(0)
    }

    override fun detachProcessImpl() {
        notifyProcessTerminated(0)
    }

    override fun startNotify() {
        super.startNotify()

        ApplicationManager.getApplication().executeOnPooledThread {
            try {
                runEmulation()
                detachProcess()
            } catch (e: Throwable) {
                notifyTextAvailable("Execution failed: ${e.message}\n", ProcessOutputTypes.STDERR)
                detachProcess()
            }
        }
    }
}
