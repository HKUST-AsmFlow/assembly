package io.github.asmflow.assembly.util.functional

fun <T, E> Option<Result<T, E>>.flatten(): Option<T> = when (this) {
    is Some -> when (data) {
        is Ok -> Some(data.value)
        is Err -> None
    }

    is None -> None
}
