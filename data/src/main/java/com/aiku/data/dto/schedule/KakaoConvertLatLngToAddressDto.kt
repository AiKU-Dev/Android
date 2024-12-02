package com.aiku.data.dto.schedule

import com.aiku.domain.model.schedule.Place
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class KakaoConvertLatLngToAddressDto(
    @Json(name = "road_address") val roadAddress: RoadAddressDto?,
    @Json(name = "address") val address: AddressDto?
)

@JsonClass(generateAdapter = true)
data class RoadAddressDto(
    @Json(name = "address_name") val addressName: String?,
    @Json(name = "region_1depth_name") val region1DepthName: String?,
    @Json(name = "region_2depth_name") val region2DepthName: String?,
    @Json(name = "region_3depth_name") val region3DepthName: String?,
    @Json(name = "road_name") val roadName: String?,
    @Json(name = "underground_yn") val undergroundYn: String?,
    @Json(name = "main_building_no") val mainBuildingNo: String?,
    @Json(name = "sub_building_no") val subBuildingNo: String?,
    @Json(name = "building_name") val buildingName: String?,
)

@JsonClass(generateAdapter = true)
data class AddressDto(
    @Json(name = "address_name") val addressName: String?,
    @Json(name = "region_1depth_name") val region1DepthName: String?,
    @Json(name = "region_2depth_name") val region2DepthName: String?,
    @Json(name = "region_3depth_name") val region3DepthName: String?,
    @Json(name = "mountain_yn") val mountainYn: String?,
    @Json(name = "main_address_no") val mainAddressNo: String?,
    @Json(name = "sub_address_no") val subAddressNo: String?
)

fun List<KakaoConvertLatLngToAddressDto>.toPlace(latitude:String, longitude:String): List<Place> {
    val lat = latitude.toDoubleOrNull() ?: 0.0
    val lng = longitude.toDoubleOrNull() ?: 0.0

    return this.map { dto ->
        val placeName = dto.roadAddress?.buildingName
            ?: dto.roadAddress?.addressName
            ?: dto.address?.addressName
            ?: "Unknown Location"

        val addressName = dto.roadAddress?.addressName
            ?: dto.address?.addressName
            ?: "Unknown Location"

        Place(
            placeName = placeName,
            addressName = addressName,
            latitude = lat,
            longitude = lng
        )
    }
}