package com.latinos.data.utils

interface ErrorHandler {
    fun getError(throwable: Throwable): Result.ErrorType
    fun getApiError(statusCode: Int, throwable: Throwable? = null): Result.ErrorType
}