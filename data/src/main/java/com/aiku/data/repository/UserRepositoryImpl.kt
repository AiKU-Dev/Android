package com.aiku.data.repository

import com.aiku.data.dto.toUserDto
import com.aiku.data.entity.toUser
import com.aiku.data.entity.toUserEntity
import com.aiku.data.source.local.UserLocalDataSource
import com.aiku.data.source.remote.UserRemoteDataSource
import com.aiku.domain.model.User
import com.aiku.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource,
    private val userRemoteDataSource: UserRemoteDataSource,
    private val coroutineDispatcher: CoroutineDispatcher
) : UserRepository {

    override fun saveUser(user: User): Flow<Unit> {
        return flow {
            emit(userRemoteDataSource.saveUser(user.toUserDto()))
            emit(userLocalDataSource.saveUser(user.toUserEntity()))
        }.flowOn(coroutineDispatcher)
    }

    override fun getUser(): Flow<User> {
        return flow {
            emit(userLocalDataSource.getUser())
        }.onStart {
            val userDto = userRemoteDataSource.getUser()
            userLocalDataSource.saveUser(userDto.toUser().toUserEntity())
        }.flowOn(coroutineDispatcher).flatMapLatest {
            it.map { userEntity -> userEntity.toUser() }
        }
    }
}
