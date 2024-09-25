package com.aiku.domain.repository

import com.aiku.domain.model.schedule.GroupScheduleOverviewPagination
import com.aiku.domain.model.schedule.request.CreateScheduleRequest
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

interface ScheduleRepository {
    fun createSchedule(groupId: Long, createScheduleRequest: CreateScheduleRequest): Flow<Long>
    fun fetchGroupSchedules(
        groupId: Long,
        page: Int,
        startDate: LocalDateTime,
        endDate: LocalDateTime
    ): Flow<GroupScheduleOverviewPagination>
}