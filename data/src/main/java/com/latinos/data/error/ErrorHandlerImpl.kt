package com.latinos.data.error

import com.latinos.data.utils.ErrorHandler
import com.latinos.domain.utils.repository.Result
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ErrorHandlerImpl @Inject constructor() : ErrorHandler {

    override fun getError(throwable: Throwable): Result.ErrorType {
        return when (throwable) {
            is IOException -> Result.ErrorType.IOError(throwable)
            is HttpException -> Result.ErrorType.HttpError(throwable, throwable.code())
            else -> Result.ErrorType.Generic(throwable)
        }
    }

    override fun getApiError(statusCode: Int, throwable: Throwable?): Result.ErrorType {
        return Result.ErrorType.HttpError(throwable, statusCode)
    }
}