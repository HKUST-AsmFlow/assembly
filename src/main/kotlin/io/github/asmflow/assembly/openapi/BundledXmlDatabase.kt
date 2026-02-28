package io.github.asmflow.assembly.openapi

import com.intellij.openapi.util.JDOMUtil
import com.intellij.util.containers.MultiMap
import org.jdom.Element
import java.net.URI

abstract class BundledXmlDatabase<K, T>(resourcePath: String) {
    val data: MultiMap<K, T> by lazy {
        parseElement(loadFile(resourcePath))
    }

    protected abstract fun parseElement(root: Element): MultiMap<K, T>

    fun loadFile(resourcePath: String): Element = JDOMUtil.loadResource(URI.create(resourcePath).toURL())
}
