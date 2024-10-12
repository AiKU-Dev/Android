package com.aiku.domain.repository

import com.aiku.domain.model.schedule.Location
import kotlinx.coroutines.flow.Flow

interface CreateScheduleRepository {
    suspend fun searchLocations(query: String): Flow<List<Location>>
    suspend fun getLocation(latitude: Double, longitude: Double): Flow<Location>
}
