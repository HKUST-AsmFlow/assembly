package io.github.asmflow.assembly.util.functional

sealed class Result<out T, out E> {
    fun ok(): Option<T> = when (this) {
        is Ok -> Some(value)
        is Err -> None
    }
}

data class Ok<T>(val value: T) : Result<T, Nothing>()
data class Err<E>(val error: E) : Result<Nothing, E>()

fun <T> resultOfException(f: () -> T): Result<T, Exception> = try {
    Ok(f())
} catch (e: Exception) {
    Err(e)
}

