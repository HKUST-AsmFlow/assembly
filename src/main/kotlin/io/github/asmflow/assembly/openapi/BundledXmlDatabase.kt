package io.github.asmflow.assembly.openapi

import org.w3c.dom.Document
import javax.xml.parsers.DocumentBuilderFactory

abstract class BundledXmlDatabase<K, T>(resourcePath: String) {
    protected val data: Map<K, T> by lazy { parseDocument(loadFile(resourcePath)) }

    protected abstract fun parseDocument(document: Document): Map<K, T>

    fun loadFile(resourcePath: String): Document =
        this::class.java.getResourceAsStream(resourcePath)?.use {
            DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(it)
        } ?: throw IllegalArgumentException("XML database not found: $resourcePath")
}
