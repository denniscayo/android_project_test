package com.latinos.data.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FlowObserverDelegate<T>(
    lifecycleOwner: LifecycleOwner,
    private val flow: Flow<T>,
    private val collector: suspend (T) -> Unit,
) {

    private var job: Job? = null

    init {
        lifecycleOwner.lifecycle.addObserver(
            LifecycleEventObserver { source: LifecycleOwner, event: Lifecycle.Event ->
                when (event) {
                    Lifecycle.Event.ON_START -> {
                        job = source.lifecycleScope.launch { flow.collect { collector(it) } }
                    }
                    Lifecycle.Event.ON_STOP -> {
                        job?.cancel()
                        job = null
                    }
                    else -> {}
                }
            }
        )
    }
}

/**
 * A sample use: viewModel.event.collectInLifeCycle(fragment) {
 * ```
 *      //event response callback
 * ```
 * }
 */
inline fun <reified T> Flow<T>.collectInLifeCycle(
    lifecycleOwner: LifecycleOwner,
    noinline collector: suspend (T) -> Unit,
) =
    FlowObserverDelegate(lifecycleOwner, this, collector)

/** A sample use: viewModel.event.onEach(::renderEvent).launchInLifeCycle(viewLifecycleOwner) */
inline fun <reified T> Flow<T>.launchInLifeCycle(lifecycleOwner: LifecycleOwner) =
    FlowObserverDelegate(lifecycleOwner, this, {})
