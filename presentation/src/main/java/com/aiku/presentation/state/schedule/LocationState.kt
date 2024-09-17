package com.aiku.presentation.state.schedule

import androidx.compose.runtime.Immutable
import com.aiku.domain.model.schedule.Location

@Immutable
data class LocationState(
    val latitude: Double,
    val longitude: Double,
    val name: String
)

fun Location.toLocationState() = LocationState(latitude, longitude, name)