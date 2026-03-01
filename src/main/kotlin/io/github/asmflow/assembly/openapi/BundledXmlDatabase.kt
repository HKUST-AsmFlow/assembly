package io.github.asmflow.assembly.openapi

import org.jdom.Document
import org.jdom.input.SAXBuilder

abstract class BundledXmlDatabase<K, T>(resourcePath: String) {
    protected val data: Map<K, T> by lazy {
        parseDocument(loadFile(resourcePath))
    }

    protected abstract fun parseDocument(document: Document): Map<K, T>

    fun loadFile(resourcePath: String): Document =
        this::class.java.getResourceAsStream(resourcePath)?.use {
            SAXBuilder().build(it)
        } ?: throw IllegalArgumentException("XML database not found: $resourcePath")
}
