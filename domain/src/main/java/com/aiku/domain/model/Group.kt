package com.aiku.domain.model

data class Group(
    val id: Long,
    val name: String,
    val description: String,
    val memberIDs: List<Long>,
)