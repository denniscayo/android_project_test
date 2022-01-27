package com.latinos.data.utils

import androidx.annotation.CheckResult
import com.latinos.domain.utils.dispatchers.DispatcherProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

abstract class FlowUseCase<T, R>(protected open val dispatcherProvider: DispatcherProvider) {

    protected open fun dispatcher(): CoroutineContext = dispatcherProvider.io()

    @CheckResult
    fun prepare(input: T) = prepareFlow(input).flowOn(dispatcher())

    /**
     * Return a [Flow] that will be executed in the specified [CoroutineContext] ([Dispatchers.IO] by default).
     *
     * There's no need to call to [flowOn] in subclasses.
     */
    protected abstract fun prepareFlow(input: T): Flow<R>
}

abstract class GenericFlowUseCase<T>(protected open val dispatcherProvider: DispatcherProvider) {

    protected open fun dispatcher(): CoroutineContext = dispatcherProvider.io()

    @CheckResult
    fun <R> prepare(input: T, klass: Class<R>) = prepareFlow(input, klass).flowOn(dispatcher())

    @CheckResult
    inline fun <reified R> prepare(input: T) = prepare(input, R::class.java)

    /**
     * Return a [Flow] that will be executed in the specified [CoroutineContext] ([Dispatchers.IO] by default).
     *
     * There's no need to call to [flowOn] in subclasses.
     */
    abstract fun <R> prepareFlow(input: T, klass: Class<R>): Flow<R?>
}
