package com.aiku.domain.repository

import com.aiku.domain.model.group.Group
import com.aiku.domain.model.group.GroupOverviewPagination
import kotlinx.coroutines.flow.Flow

interface GroupRepository {
    fun createGroup(name: String): Flow<Group>
    fun deleteGroup(groupId: Long): Flow<Unit>
    fun fetchGroups(): Flow<GroupOverviewPagination>
    fun fetchGroup(groupId: Long): Flow<Group>
    fun enterGroup(groupId: Long): Flow<Unit>
    fun exitGroup(groupId: Long): Flow<Unit>
}