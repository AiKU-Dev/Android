package com.aiku.data.source.remote

import com.aiku.data.api.remote.ScheduleApi
import com.aiku.data.dto.IdDto
import com.aiku.data.dto.schedule.GroupScheduleOverviewPaginationDto
import com.aiku.data.dto.schedule.request.CreateScheduleRequestDto
import java.time.LocalDateTime
import javax.inject.Inject

class ScheduleRemoteDataSource @Inject constructor(
    private val scheduleApi: ScheduleApi
) {

    suspend fun createSchedule(groupId: Long, createScheduleRequest: CreateScheduleRequestDto): IdDto {
        return scheduleApi.createSchedule(groupId, createScheduleRequest)
    }

    suspend fun fetchGroupSchedules(
        groupId: Long,
        page: Int,
        startDate: LocalDateTime,
        endTime: LocalDateTime
    ): GroupScheduleOverviewPaginationDto {
        return scheduleApi.fetchGroupSchedules(groupId, page, startDate, endTime)
    }
}
