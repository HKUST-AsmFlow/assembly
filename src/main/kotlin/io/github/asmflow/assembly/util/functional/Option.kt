package io.github.asmflow.assembly.util.functional

sealed class Option<out T> {
    fun isSome(): Boolean = this is Some
}

data class Some<T>(val data: T) : Option<T>()
data object None : Option<Nothing>()

fun <T> T?.toOption(): Option<T> = optionOf(this)

fun <T> optionOf(value: T?): Option<T> =
    if (value == null) None else Some(value)
