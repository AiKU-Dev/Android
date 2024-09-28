package com.aiku.domain.repository

import com.aiku.domain.model.group.Group
import com.aiku.domain.model.group.GroupOverviewPagination
import kotlinx.coroutines.flow.Flow

interface GroupRepository {
    fun createGroup(name: String): Flow<Result<Group>>
    fun fetchGroups(): Flow<GroupOverviewPagination>
}