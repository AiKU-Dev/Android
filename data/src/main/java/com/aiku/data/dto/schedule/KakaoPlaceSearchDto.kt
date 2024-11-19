package com.aiku.data.dto.schedule

import com.aiku.domain.model.schedule.Place
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class KakaoPlaceSearchDto (
    @Json(name = "place_name") val placeName: String,
    @Json(name = "distance") val distance: String?,
    @Json(name = "place_url") val placeUrl: String?,
    @Json(name = "category_name") val categoryName: String?,
    @Json(name = "address_name") val addressName: String?,
    @Json(name = "road_address_name") val roadAddressName: String?,
    @Json(name = "id") val id: String?,
    @Json(name = "phone") val phone: String?,
    @Json(name = "category_group_code") val categoryGroupCode: String?,
    @Json(name = "category_group_name") val categoryGroupName: String?,
    @Json(name = "x") val longitude: String,
    @Json(name = "y") val latitude: String
)

fun List<KakaoPlaceSearchDto>.toPlaceList(): List<Place> {
    return this.map {
        Place(
            placeName = it.placeName,
            addressName = it.roadAddressName ?: it.addressName ?: "Unknown Location",
            latitude = it.latitude.toDoubleOrNull() ?: 0.0,
            longitude = it.longitude.toDoubleOrNull() ?: 0.0
        )
    }
}