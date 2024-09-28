package com.aiku.data.repository

import com.aiku.data.dto.IdDto
import com.aiku.data.dto.group.request.CreateGroupRequestDto
import com.aiku.data.source.remote.GroupRemoteDataSource
import com.aiku.domain.exception.ErrorResponse
import com.aiku.domain.exception.UNKNOWN
import com.aiku.domain.model.group.Group
import com.aiku.domain.model.group.GroupOverviewPagination
import com.aiku.domain.repository.GroupRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class GroupRepositoryImpl @Inject constructor(
    private val groupRemoteDataSource: GroupRemoteDataSource,
    private val coroutineDispatcher: CoroutineDispatcher

) : GroupRepository {

    private val myGroups: StateFlow<GroupOverviewPagination> = fetchGroups()
        .flowOn(coroutineDispatcher)
        .stateIn(
            scope = CoroutineScope(coroutineDispatcher),
            started = SharingStarted.Lazily,
            initialValue = GroupOverviewPagination(1, emptyList())
        )

    override fun createGroup(name: String): Flow<Result<Group>> {
        return flow {
            val result: Result<IdDto> =
                groupRemoteDataSource.createGroup(CreateGroupRequestDto(groupName = name))

            result.fold(
                onSuccess = { idDto ->
                    val group = Group(
                        id = idDto.id ?: 0L,
                        name = name,
                        members = emptyList()
                    )
                    emit(Result.success(group))
                },
                onFailure = { exception -> throw exception } // onError에서 처리
            )
        }.flowOn(Dispatchers.IO)
            .catch { e -> emit(Result.failure(ErrorResponse(UNKNOWN, e.message ?: "알 수 없는 오류 발생"))) }
    }

    override fun fetchGroups(): Flow<GroupOverviewPagination> {
        return myGroups
    }
}