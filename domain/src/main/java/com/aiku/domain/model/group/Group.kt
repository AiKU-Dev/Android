package com.aiku.domain.model.group

data class Group(
    val id: Long,
    val name: String,
    val members: List<String>, //TODO : 수정
)