package com.aiku.domain.model

data class User(
    val image: String,
    val name: String,
    val phoneNumber: String,
    val groups: List<Group>,
)