package ngoding.modol.cleanote

import kotlinx.coroutines.CoroutineDispatcher

data class Dispatcher (
    val main: CoroutineDispatcher,
    val io: CoroutineDispatcher
)