package com.aiku.domain.model.group

data class Group(
    val id: Long,
    val name: String,
    val members: List<Member>,
)