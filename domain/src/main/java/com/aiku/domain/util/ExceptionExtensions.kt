package com.aiku.domain.util

import kotlin.coroutines.cancellation.CancellationException

suspend fun <T> runSuspendCatching(
    block: suspend () -> T
) : Result<T> {
    return try {
        Result.success(block())
    } catch(e: CancellationException) {
        throw e
    } catch (e: Exception) {
        Result.failure(e)
    }
}