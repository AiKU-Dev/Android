package com.aiku.data.dto.schedule

import com.aiku.domain.model.Location
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LocationDto(
    @Json(name = "latitude") val latitude: Double?,
    @Json(name = "longitude") val longitude: Double?,
    @Json(name = "locationName") val locationName: String?
) {

    fun toLocation(): Location {
        return Location(
            latitude = latitude ?: 0.0,
            longitude = longitude ?: 0.0,
            locationName = locationName ?: ""
        )
    }
}