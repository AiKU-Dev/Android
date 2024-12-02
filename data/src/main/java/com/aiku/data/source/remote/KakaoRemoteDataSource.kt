package com.aiku.data.source.remote

import android.util.Log
import com.aiku.data.api.remote.KakaoRestApi
import com.aiku.data.dto.schedule.KakaoConvertLatLngToAddressDto
import com.aiku.data.dto.schedule.KakaoPlaceSearchDto
import com.aiku.domain.exception.ErrorResponse
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/** Kakao Rest Api 전용 */
class KakaoRemoteDataSource @Inject constructor(
    private val kakaoRestApi: KakaoRestApi
) {
    suspend fun searchPlacesByKeyword(query : String): List<KakaoPlaceSearchDto> {
        return try {
            kakaoRestApi.searchPlacesByKeyword(query)
        } catch (e: HttpException) {
            throw ErrorResponse(
                code = e.code(),
                message = e.response()?.errorBody()?.string() ?: "Unknown HTTP error"
            )
        } catch (e: IOException) {
            throw ErrorResponse(
                code = -1,
                message = "Network connection error: ${e.message}"
            )
        } catch (e: Exception) {
            throw ErrorResponse(
                code = -2,
                message = "Unexpected error: ${e.message}"
            )
        }
    }

    suspend fun convertLatLngToAddress(latitude: String, longitude: String): List<KakaoConvertLatLngToAddressDto> {
        return try {
            kakaoRestApi.convertLatLngToAddress(latitude = latitude, longitude = longitude)
        } catch (e: HttpException) {
            throw ErrorResponse(
                code = e.code(),
                message = e.response()?.errorBody()?.string() ?: "Unknown HTTP error"
            )
        } catch (e: IOException) {
            throw ErrorResponse(
                code = -1,
                message = "Network connection error: ${e.message}"
            )
        } catch (e: Exception) {
            throw ErrorResponse(
                code = -2,
                message = "Unexpected error: ${e.message}"
            )
        }
    }
}

