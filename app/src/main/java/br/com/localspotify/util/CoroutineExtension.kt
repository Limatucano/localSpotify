package br.com.localspotify.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun <F,S> CoroutineScope.launchSuspendZip(
    firstBlock: suspend CoroutineScope.() -> F,
    secondBlock: suspend CoroutineScope.() -> S,
    delayMillis: Long = 100L,
    onLoading: suspend (Boolean) -> Unit = {},
    onError: suspend (Throwable?) -> Unit = {},
    onSuccess: suspend (Pair<F,S>) -> Unit = {}
) {
    launch(Dispatchers.IO) {
        onLoading(true)
        try {
            delay(delayMillis)
            val firstResult = async { firstBlock() }
            val secondResult = async { secondBlock() }

            val result = firstResult.await() to secondResult.await()

            onSuccess(result)
        } catch (e: Exception) {
            onError(e)
        } finally {
            delay(50)
            onLoading(false)
        }
    }
}