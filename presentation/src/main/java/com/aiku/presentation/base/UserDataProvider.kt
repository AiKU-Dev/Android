package com.aiku.presentation.base

import com.aiku.domain.repository.UserRepository
import com.aiku.presentation.state.UserState
import com.aiku.presentation.state.toUserState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

interface UserDataProvider {
    val user: StateFlow<UserState>
}

class UserDataProviderImpl @Inject constructor(
    userRepository: UserRepository,
    coroutineScope: CoroutineScope
) : UserDataProvider {

    override val user: StateFlow<UserState> = userRepository.getUser().map {
        it.toUserState()
    }.stateIn(
        scope = coroutineScope,
        started = SharingStarted.Lazily,
        initialValue = UserState(
            image = "",
            nickname = "",
            phoneNumber = "",
            groups = emptyList()
        )
    )
}