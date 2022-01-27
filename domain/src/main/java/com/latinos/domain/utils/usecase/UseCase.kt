package com.latinos.domain.utils.usecase

interface UseCase<in Params, out T> {
    fun execute(params: Params): T
}