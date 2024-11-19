package com.aiku.domain.usecase.schedule

import com.aiku.domain.model.schedule.Place
import com.aiku.domain.repository.KakaoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchPlacesByKeywordUseCase @Inject constructor(
    private val kakaoRepository: KakaoRepository
) {
    suspend operator fun invoke(query: String): Flow<List<Place>> {
        return kakaoRepository.searchPlacesByKeyword(query)
    }
}