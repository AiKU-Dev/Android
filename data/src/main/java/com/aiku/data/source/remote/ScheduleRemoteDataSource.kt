package com.aiku.data.source.remote

import android.util.Log
import com.aiku.data.api.remote.ScheduleApi
import com.aiku.data.dto.IdDto
import com.aiku.data.dto.schedule.GroupScheduleOverviewPaginationDto
import com.aiku.data.dto.schedule.LocationDto
import com.aiku.data.dto.schedule.UserScheduleOverviewDto
import com.aiku.data.dto.schedule.UserScheduleOverviewPaginationDto
import com.aiku.data.dto.schedule.ScheduleDto
import com.aiku.data.dto.schedule.request.BetAkuReqDto
import com.aiku.data.dto.schedule.request.CreateScheduleReqDto
import com.aiku.domain.model.schedule.type.ScheduleStatus
import java.time.LocalDateTime
import javax.inject.Inject
import kotlin.random.Random

class ScheduleRemoteDataSource @Inject constructor(
    private val scheduleApi: ScheduleApi
) {

    suspend fun createSchedule(groupId: Long, createScheduleRequest: CreateScheduleReqDto): IdDto {
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

    suspend fun fetchGroupScheduleDetail(groupId: Long, scheduleId: Long): ScheduleDto {
        return scheduleApi.fetchGroupScheduleDetail(groupId, scheduleId)
    }

    suspend fun bet(scheduleId: Long, betAkuRequest: BetAkuReqDto) {
        return scheduleApi.bet(scheduleId, betAkuRequest)
    }


    suspend fun fetchUserSchedules(
        page: Int,
        startDate: LocalDateTime,
        endDate: LocalDateTime
    ): UserScheduleOverviewPaginationDto {
        return scheduleApi.fetchUserSchedules(page, startDate, endDate)
    }
}
