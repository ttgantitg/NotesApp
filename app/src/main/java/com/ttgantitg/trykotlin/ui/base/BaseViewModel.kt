package com.ttgantitg.trykotlin.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlin.coroutines.CoroutineContext

open class BaseViewModel<S> : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext by lazy {
        Dispatchers.Default + Job()
    }

    private val errorChannel = Channel<Throwable>()
    @ExperimentalCoroutinesApi
    private val viewStateChannel = BroadcastChannel<S>(Channel.CONFLATED)

    @ExperimentalCoroutinesApi
    fun getViewState(): ReceiveChannel<S> = viewStateChannel.openSubscription()
    fun getErrorChannel(): ReceiveChannel<Throwable> = errorChannel

    protected fun setError(e: Throwable){
        launch {
            errorChannel.send(e)
        }
    }

    @ExperimentalCoroutinesApi
    protected fun setData(data: S) {
        launch {
            viewStateChannel.send(data)
        }
    }

    @ExperimentalCoroutinesApi
    override fun onCleared() {
        viewStateChannel.close()
        errorChannel.close()
        coroutineContext.cancel()
        super.onCleared()
    }
}