package com.aiku.data.delegate

import com.aiku.domain.delegate.UserDelegate
import com.aiku.domain.model.UserModel
import javax.inject.Inject

class UserDelegateImpl @Inject constructor() : UserDelegate {

    private var currentUser: UserModel? = null

    override fun saveUser(user: UserModel) {
        currentUser = user
    }

    override fun getCurrentUser(): UserModel? {
        return currentUser
    }
}