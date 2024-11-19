package com.aiku.data.api.remote

import com.aiku.data.dto.schedule.KakaoPlaceSearchDto
import retrofit2.http.GET
import retrofit2.http.Query

interface KakaoRestApi {

    /** 키워드로 장소 검색 */
    @GET("v2/local/search/keyword.json")
    suspend fun searchPlacesByKeyword(
        @Query("query") query: String
    ): List<KakaoPlaceSearchDto>
}