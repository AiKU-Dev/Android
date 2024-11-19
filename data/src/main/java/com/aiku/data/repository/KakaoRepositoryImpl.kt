package com.aiku.data.repository

import com.aiku.data.dto.schedule.toPlaceList
import com.aiku.data.source.remote.KakaoRemoteDataSource
import com.aiku.domain.model.schedule.Place
import com.aiku.domain.repository.KakaoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/** Kakao Rest Api 전용 */
class KakaoRepositoryImpl @Inject constructor(
    private val kakaoRemoteDataSource: KakaoRemoteDataSource
) : KakaoRepository {
    override suspend fun searchPlacesByKeyword(query: String): Flow<List<Place>> {
        return flow {
            emit(kakaoRemoteDataSource.searchPlacesByKeyword(query).toPlaceList())
        }
    }
}