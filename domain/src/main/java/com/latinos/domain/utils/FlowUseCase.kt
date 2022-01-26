package com.latinos.domain.utils

interface FlowUseCase<in Params, out T> {
    fun execute(params: Params): T
}