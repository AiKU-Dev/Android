package com.aiku.data.api.remote

import LocationDto
import com.aiku.data.dto.IdDto
import com.aiku.data.dto.group.GroupDto
import com.aiku.data.dto.group.GroupOverviewPaginationDto
import com.aiku.data.dto.group.request.CreateGroupRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CreateScheduleApi {
    @GET("v2/local/geo/coord2address.json")
    suspend fun getLocation(
        @Query("x") longitude: Double,
        @Query("y") latitude: Double,
        @Query("input_coord") inputCoord: String = "WGS84"
    ): LocationDto
}