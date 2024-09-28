package com.aiku.data.api.remote

import com.aiku.data.dto.IdDto
import com.aiku.data.dto.group.GroupDto
import com.aiku.data.dto.group.GroupOverviewPaginationDto
import com.aiku.data.dto.group.request.CreateGroupRequestDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface GroupApi {

    @POST("groups")
    suspend fun createGroup(@Body request: CreateGroupRequestDto): Response<IdDto>

    @GET("groups")
    suspend fun fetchGroups(): GroupOverviewPaginationDto
}