package com.aiku.data.source.remote

import com.aiku.data.api.remote.GroupApi
import com.aiku.data.dto.IdDto
import com.aiku.data.dto.group.GroupDto
import com.aiku.data.dto.group.GroupOverviewPaginationDto
import com.aiku.data.dto.group.request.CreateGroupRequestDto
import javax.inject.Inject

class GroupRemoteDataSource @Inject constructor(
    private val groupApi: GroupApi
) {

    suspend fun createGroup(request: CreateGroupRequestDto): IdDto {
        return groupApi.createGroup(request)
    }

    suspend fun deleteGroup(groupId: Long) {
        groupApi.deleteGroup(groupId)
    }

    suspend fun fetchGroups(): GroupOverviewPaginationDto {
        return groupApi.fetchGroups()
    }

    suspend fun fetchGroup(groupId: Long): GroupDto {
        return groupApi.fetchGroup(groupId)
    }

    suspend fun enterGroup(groupId: Long) {
        groupApi.enterGroup(groupId)
    }

    suspend fun exitGroup(groupId: Long) {
        groupApi.exitGroup(groupId)
    }
}