package com.aiku.presentation.util

import com.aiku.domain.exception.ErrorResponse
import com.aiku.domain.exception.UNKNOWN
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

internal fun<T> Flow<T>.onError(action: suspend (ErrorResponse) -> Unit): Flow<T> {
    return this.catch { e ->
        if (e !is ErrorResponse) {
            throw ErrorResponse(UNKNOWN, e.message ?: "Unknown error")
        }
        action(e)
    }
}