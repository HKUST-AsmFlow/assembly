package io.github.asmflow.assembly.util.functional

fun <T> Option<Option<T>>.flatten(): Option<T> = when (this) {
    is Some -> when (data) {
        is Some -> Some(data.data)
        is None -> None
    }

    is None -> None
}
