package io.github.asmflow.assembly.util.functional

sealed class Result<out T, out E> {
    fun isErr(): Boolean = this is Err

    fun <E2> mapErr(f: (E) -> E2): Result<T, E2> = when (this) {
        is Ok -> this
        is Err -> Err(f(error))
    }

    fun unwrapErr(): E = when (this) {
        is Ok -> throw IllegalStateException("called .unwrapErr() on an Ok value")
        is Err -> error
    }
}

data class Ok<T>(val value: T) : Result<T, Nothing>()
data class Err<E>(val error: E) : Result<Nothing, E>()

fun <T> resultOfException(f: () -> T): Result<T, Exception> = try {
    Ok(f())
} catch (e: Exception) {
    Err(e)
}
