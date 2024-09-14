package com.aiku.domain.model

data class Group(
    val id: Long,
    val name: String,
    val members: List<Member>,
)