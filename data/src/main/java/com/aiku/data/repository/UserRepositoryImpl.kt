package com.aiku.data.repository

import com.aiku.data.dto.user.UserDto
import com.aiku.data.dto.user.toNewUserDto
import com.aiku.data.dto.user.toTerms
import com.aiku.data.dto.user.toUserDto
import com.aiku.data.source.local.UserLocalDataSource
import com.aiku.data.source.remote.UserRemoteDataSource
import com.aiku.domain.exception.ALREADY_EXIST_NICKNAME
import com.aiku.domain.exception.ErrorResponse
import com.aiku.domain.model.user.NewUser
import com.aiku.domain.model.user.Terms
import com.aiku.domain.model.user.User
import com.aiku.domain.repository.UserRepository
import com.aiku.domain.util.runSuspendCatching
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource,
    private val userRemoteDataSource: UserRemoteDataSource,
    private val coroutineDispatcher: CoroutineDispatcher
) : UserRepository {

    private val user = flow {
        emit(userRemoteDataSource.fetchUser())
    }.map {
        it.toUser()
    }.stateIn(
        scope = CoroutineScope(coroutineDispatcher),
        started = SharingStarted.Lazily,
        initialValue = UserDto.EMPTY.toUser()
    )

    override suspend fun saveUser(user: NewUser): Result<Unit> {
        return runSuspendCatching {
            userRemoteDataSource.saveUser(user.toNewUserDto())
        }
    }

    override fun fetchUser(): Flow<User> {
        return user
    }

    override suspend fun fetchTerms(): Terms {
        return userRemoteDataSource.fetchTerms().toTerms()
    }

    override suspend fun fetchIsNicknameExist(nickname: String): Result<Unit> {
        return if (userRemoteDataSource.fetchIsNicknameExist(nickname).exist)
            return Result.failure(ErrorResponse(ALREADY_EXIST_NICKNAME))
        else Result.success(Unit)
    }
}
