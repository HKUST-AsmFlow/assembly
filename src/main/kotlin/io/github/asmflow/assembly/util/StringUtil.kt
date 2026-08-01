package io.github.asmflow.assembly.util

fun String.removeFirstMatchingPrefix(prefixes: Iterable<String>): String {
    val prefix = prefixes.firstOrNull { startsWith(it) } ?: return this
    return removePrefix(prefix)
}
