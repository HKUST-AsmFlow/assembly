package io.github.asmflow.assembly.armv7.util

fun String.removeFirstMatchingPrefix(prefixes: Iterable<String>): String {
    val prefix = prefixes.firstOrNull { startsWith(it) } ?: return this
    return removePrefix(prefix)
}
