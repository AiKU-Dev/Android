package com.aiku.domain.repository

import com.aiku.domain.model.schedule.GroupScheduleOverviewPagination
import com.aiku.domain.model.schedule.request.CreateScheduleReq
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

interface ScheduleRepository {
    fun createSchedule(groupId: Long, createScheduleReq: CreateScheduleReq): Flow<Long>
    fun fetchGroupSchedules(
        groupId: Long,
        page: Int,
        startDate: LocalDateTime,
        endDate: LocalDateTime
    ): Flow<GroupScheduleOverviewPagination>
}