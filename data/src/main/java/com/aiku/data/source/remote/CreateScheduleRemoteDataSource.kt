package com.aiku.data.source.remote

import LocationDto
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import com.aiku.data.api.remote.CreateScheduleApi
import com.aiku.domain.exception.ErrorResponse
import com.aiku.domain.exception.UNKNOWN
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CreateScheduleRemoteDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
    private val createScheduleApi: CreateScheduleApi,
    private val coroutineDispatcher: CoroutineDispatcher
) {

    suspend fun getLocation(latitude: Double, longitude: Double): LocationDto {
        return withContext(coroutineDispatcher) {
            try {
                createScheduleApi.getLocation(longitude, latitude)
            } catch (e: Exception) {
                throw ErrorResponse(UNKNOWN, "Network request failed: ${e.message}")
            }
        }
    }
}
