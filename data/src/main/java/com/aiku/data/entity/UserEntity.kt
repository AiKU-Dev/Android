package com.aiku.data.entity

import com.aiku.data.UserEntity
import com.aiku.domain.model.User

/**
 * @see com.aiku.data.UserEntity
 */
fun User.toUserEntity(): UserEntity {
    return UserEntity.getDefaultInstance().toBuilder()
        .setImage(image)
        .setName(name)
        .setPhoneNumber(phoneNumber)
        .addAllGroups(groups.map { it.toGroupEntity() })
        .build()
}

fun UserEntity.toUser(): User {
    return User(
        image = image,
        name = name,
        phoneNumber = phoneNumber,
        groups = groupsList.map { it.toGroup() }
    )
}