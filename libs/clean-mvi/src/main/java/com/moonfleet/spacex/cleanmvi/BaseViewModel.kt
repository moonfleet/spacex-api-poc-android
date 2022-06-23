package com.moonfleet.spacex.cleanmvi

import androidx.lifecycle.*
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import timber.log.Timber

abstract class BaseViewModel<S : State, A : Action, E : Effect>(val initialState: S, val errorStateBuilder: (String) -> S) : ViewModel() {

    var state = MutableStateFlow(initialState)

    private var effectChannel = BroadcastChannel<E>(Channel.BUFFERED)
    @FlowPreview
    var effectFlow = effectChannel.asFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler{ _, t ->
        Timber.e(t)
        emitState(errorStateBuilder(t.message ?: "Unknown error"))
    }

    protected abstract fun onAction(state: S, action: A)

    fun action(action: A) {
        Timber.e("action: $action")
        onAction(state.value, action)
    }

    @OptIn(InternalCoroutinesApi::class)
    @FlowPreview
    fun observeEffects(
        lifecycleOwner: LifecycleOwner,
        onEffectObserved: (E) -> Unit
    ) {
        viewModelScope.launch {
            effectFlow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED).collect {
                onEffectObserved(it)
            }
        }
    }

    fun launch(
        context: CoroutineDispatcher = Dispatchers.Default,
        block: suspend CoroutineScope.() -> Unit
    ) = viewModelScope.launch(context = context + coroutineExceptionHandler, block = block)

    protected fun emitState(newState: S) {
        launch(context = Dispatchers.Main) {
            state.value = newState
        }
    }

    protected fun emitEffect(effect: E) {
        launch(context = Dispatchers.Main) {
            effectChannel.send(effect)
        }
    }
}