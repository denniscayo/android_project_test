package com.latinos.data.utils

sealed class Result<out T>(
    val data: T? = null,
    val error: ErrorType? = null,
) {
    class Success<T>(data: T) : Result<T>(data)
    class Error<T>(error: ErrorType? = null, data: T? = null) : Result<T>(data, error)

    sealed class ErrorType(
        val throwable: Throwable? = null,
        val message: Int? = null,
    ) {
        class Generic(throwable: Throwable? = null) : ErrorType(throwable)
    }
}