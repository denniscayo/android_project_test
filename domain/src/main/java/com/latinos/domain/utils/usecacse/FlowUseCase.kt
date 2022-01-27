package com.latinos.domain.utils.usecacse

interface FlowUseCase<in Params, out T> {
    fun execute(params: Params): T
}