package com.aiku.presentation.state.group

import androidx.compose.runtime.Immutable
import com.aiku.domain.model.Location

@Immutable
data class LocationState(
    val latitude: Double,
    val longitude: Double,
    val locationName: String
)

fun Location.toLocationState(): LocationState {
    return LocationState(
        latitude = latitude,
        longitude = longitude,
        locationName = locationName
    )
}