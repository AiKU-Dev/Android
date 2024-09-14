package com.aiku.data.repository

import com.aiku.data.source.remote.GroupRemoteDataSource
import com.aiku.domain.model.group.Group
import com.aiku.domain.model.group.GroupOverviewPagination
import com.aiku.domain.repository.GroupRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
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

    override fun createGroup(): Flow<Group> {
        return flow {
            emit(groupRemoteDataSource.createGroup().toGroup())
        }.flowOn(coroutineDispatcher)
    }

    override fun deleteGroup(groupId: Long): Flow<Unit> {
        return flow {
            emit(groupRemoteDataSource.deleteGroup(groupId))
        }.flowOn(coroutineDispatcher)
    }

    override fun fetchGroups(): Flow<GroupOverviewPagination> {
        return myGroups
    }

    override fun fetchGroup(groupId: Long): Flow<Group> {
        return flow {
            emit(groupRemoteDataSource.fetchGroup(groupId).toGroup())
        }.flowOn(coroutineDispatcher)
    }

    override fun enterGroup(groupId: Long): Flow<Unit> {
        return flow {
            emit(groupRemoteDataSource.enterGroup(groupId))
        }.flowOn(coroutineDispatcher)
    }

    override fun exitGroup(groupId: Long): Flow<Unit> {
        return flow {
            emit(groupRemoteDataSource.exitGroup(groupId))
        }.flowOn(coroutineDispatcher)
    }
}