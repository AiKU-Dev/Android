package com.aiku.domain.repository

import com.aiku.domain.model.schedule.Place
import kotlinx.coroutines.flow.Flow

/** Kakao Rest Api 전용 */
interface KakaoRepository {
    suspend fun searchPlacesByKeyword(query: String): Flow<List<Place>>
}