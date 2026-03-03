package io.github.asmflow.assembly.util.functional

sealed class Option<out T> {
    fun alsoIfSome(f: (T) -> Unit) = when (this) {
        is Some -> f(data)
        is None -> Unit
    }

    fun isSome(): Boolean = this is Some

    fun <R> map(f: (T) -> R): Option<R> = when (this) {
        is Some -> Some(f(data))
        is None -> None
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
