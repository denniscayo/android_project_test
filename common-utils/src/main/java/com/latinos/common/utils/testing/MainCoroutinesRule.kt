package com.latinos.common.utils.testing

import com.latinos.common.utils.dispatchers.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

@ExperimentalCoroutinesApi
class MainCoroutinesRule : TestRule, TestCoroutineScope by TestCoroutineScope() {

    private val testCoroutinesDispatcher = TestCoroutineDispatcher()
    private val testCoroutinesScope = TestCoroutineScope(testCoroutinesDispatcher)

    val testDispatcherProvider =
        object : DispatcherProvider {
            override fun default(): CoroutineDispatcher = testCoroutinesDispatcher
            override fun io(): CoroutineDispatcher = testCoroutinesDispatcher
            override fun main(): CoroutineDispatcher = testCoroutinesDispatcher
            override fun unconfined(): CoroutineDispatcher = testCoroutinesDispatcher
        }

    override fun apply(base: Statement?, description: Description?) =
        object : Statement() {
            override fun evaluate() {

                Dispatchers.setMain(testCoroutinesDispatcher)

                base?.evaluate()

                cleanupTestCoroutines()
                Dispatchers.resetMain()
            }
        }

    fun runTest(block: suspend TestCoroutineScope.() -> Unit) =
        testCoroutinesScope.runBlockingTest(block)
}
