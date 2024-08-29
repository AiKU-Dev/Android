package com.aiku.data.entity

import com.aiku.data.UserEntity.GroupEntity
import com.aiku.domain.model.Group

/**
 * @see com.aiku.data.UserEntity
 */
fun Group.toGroupEntity(): GroupEntity {
    return GroupEntity.getDefaultInstance().toBuilder()
        .setId(id)
        .setName(name)
        .setDescription(description)
        .addAllMembers(memberIDs)
        .build()
}

fun GroupEntity.toGroup(): Group {
    return Group(
        id = id,
        name = name,
        description = description,
        memberIDs = membersList.toList()
    )
}