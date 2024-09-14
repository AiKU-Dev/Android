package com.aiku.data.api.remote

import com.aiku.data.dto.group.GroupDto
import com.aiku.data.dto.group.GroupOverviewPaginationDto
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface GroupApi {

    @POST("groups")
    suspend fun createGroup(): GroupDto

    @DELETE("groups/{groupId}")
    suspend fun deleteGroup(@Path("groupId") groupId: Long)

    @GET("groups")
    suspend fun fetchGroups(): GroupOverviewPaginationDto

    @GET("groups/{groupId}")
    suspend fun fetchGroup(@Path("groupId") groupId: Long): GroupDto

    @POST("groups/{groupId}/enter")
    suspend fun enterGroup(@Path("groupId") groupId: Long)

    @POST("groups/{groupId}/exit")
    suspend fun exitGroup(@Path("groupId") groupId: Long)
}