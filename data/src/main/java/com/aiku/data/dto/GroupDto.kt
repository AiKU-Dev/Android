package com.aiku.data.dto

import com.aiku.domain.model.Group
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GroupDto(
    @Json(name = "id") val id: Long,
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String,
    @Json(name = "users") val memberIDs: List<Long>,
) {

    fun toGroup() = Group(
        id = id,
        name = name,
        description = description,
        memberIDs = memberIDs
    )
}

fun Group.toGroupDto() = GroupDto(
    id = id,
    name = name,
    description = description,
    memberIDs = memberIDs
)