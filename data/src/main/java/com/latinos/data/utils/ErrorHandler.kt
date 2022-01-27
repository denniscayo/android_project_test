package com.latinos.data.utils

import com.latinos.domain.utils.repository.Result

interface ErrorHandler {
    fun getError(throwable: Throwable): Result.ErrorType
    fun getApiError(statusCode: Int, throwable: Throwable? = null): Result.ErrorType
}