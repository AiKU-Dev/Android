package com.aiku.data.api.remote

import com.aiku.data.dto.IdDto
import com.aiku.data.dto.schedule.GroupScheduleOverviewPaginationDto
import com.aiku.data.dto.schedule.request.CreateScheduleRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.time.LocalDateTime

interface ScheduleApi {

    @POST("groups/{groupId}/schedules")
    suspend fun createSchedule(
        @Path("groupId") groupId: Long,
        @Body createScheduleRequest: CreateScheduleRequest
    ): IdDto

    @GET("groups/{groupId}/schedules")
    suspend fun fetchGroupSchedules(
        @Path("groupId") groupId: Long,
        @Query("page") page: Int,
        @Query("startDate") startDate: LocalDateTime,
        @Query("endDate") endDate: LocalDateTime
    ): GroupScheduleOverviewPaginationDto
}