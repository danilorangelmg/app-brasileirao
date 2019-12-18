package br.com.soccer.common

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.*

class OnClickLoader<out T>(val lifecycle: Lifecycle,
                           val view: View,
                           val loadFunction: () -> T) {
    infix fun then(uiFunction: (T) -> Unit) {
        val job = Job()
        val actor = GlobalScope.actor<Unit>(context = Dispatchers.Main) {
            channel.map { loadFunction() }
                .consumeEach { uiFunction(it) }
        }

        lifecycle.addObserver(LifecycleListener(job))

        view.setOnClickListener { actor.offer(Unit) }
    }
}

class LifecycleListener(val job: Job) : LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop() {
        if (!job.isCancelled) {
            job.cancel()
        }
    }
}

fun <T> LifecycleOwner.whenClicking(view: View,
                                    loadFunction: () -> T):
        OnClickLoader<T> {
    return OnClickLoader(lifecycle = lifecycle,
        view = view,
        loadFunction = loadFunction)
}