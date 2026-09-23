package io.github.asmflow.assembly.armv7
import com.intellij.DynamicBundle
import org.jetbrains.annotations.PropertyKey

const val BUNDLE = "armv7.messages.AssemblyBundle"

object AssemblyBundle : DynamicBundle(BUNDLE) {
    @JvmStatic
    fun message(@PropertyKey(resourceBundle = BUNDLE) key: String, vararg params: Any) =
        getMessage(key, *params)

    @JvmStatic
    fun messagePointer(@PropertyKey(resourceBundle = BUNDLE) key: String, vararg params: Any) =
        getLazyMessage(key, *params)
}
