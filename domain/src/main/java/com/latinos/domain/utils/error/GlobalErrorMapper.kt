package com.latinos.domain.utils.error

interface GlobalErrorMapper {
    fun map(throwable: Throwable): GlobalErrorType
}
