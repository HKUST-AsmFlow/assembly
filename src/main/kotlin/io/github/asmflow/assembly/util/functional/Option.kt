package io.github.asmflow.assembly.util.functional

sealed class Option<out T> {
    fun isSome(): Boolean = this is Some

    fun <R> map(f: (T) -> R): Option<R> = when (this) {
        is Some -> Some(f(data))
        is None -> None
    }

    fun unwrap(): T = when (this) {
        is Some -> data
        is None -> error("called .unwrap() on None value")
    }

    fun unwrapOr(default: @UnsafeVariance T): T = when (this) {
        is Some -> data
        is None -> default
    }
}

data class Some<T>(val data: T) : Option<T>()
data object None : Option<Nothing>()

fun <T> T?.toOption(): Option<T> = optionOf(this)

fun <T> optionOf(value: T?): Option<T> =
    if (value == null) None else Some(value)
