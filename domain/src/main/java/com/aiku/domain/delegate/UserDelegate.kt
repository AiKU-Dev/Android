package com.aiku.domain.delegate

import com.aiku.domain.model.UserModel

interface UserDelegate {
    fun saveUser(user: UserModel)
    fun getCurrentUser(): UserModel?
}
