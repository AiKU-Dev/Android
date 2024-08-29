package com.aiku.domain.exception

data class ErrorResponse(
    val code: Int,
    override val message: String,
) : Exception()