package io.github.asmflow.assembly.util.functional

sealed class Option<out T> {
    fun isNone(): Boolean = this is None
}

data class Some<T>(val data: T) : Option<T>()
data object None : Option<Nothing>()

fun <T> optionOf(value: T?): Option<T> =
    if (value == null) None else Some(value)
