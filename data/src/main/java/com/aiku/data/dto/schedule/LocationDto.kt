import com.aiku.domain.model.schedule.Location
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LocationDto(
    @Json(name = "meta") val meta: Meta,
    @Json(name = "documents") val documents: List<Document>
) {
    @JsonClass(generateAdapter = true)
    data class Meta(
        @Json(name = "total_count") val totalCount: Int
    )

    @JsonClass(generateAdapter = true)
    data class Document(
        @Json(name = "road_address") val roadAddress: RoadAddress?,
        @Json(name = "address") val address: Address
    )

    @JsonClass(generateAdapter = true)
    data class RoadAddress(
        @Json(name = "address_name") val addressName: String,
        @Json(name = "region_1depth_name") val region1depthName: String,
        @Json(name = "region_2depth_name") val region2depthName: String,
        @Json(name = "region_3depth_name") val region3depthName: String?,
        @Json(name = "road_name") val roadName: String?,
        @Json(name = "main_building_no") val mainBuildingNo: String?,
        @Json(name = "sub_building_no") val subBuildingNo: String?,
        @Json(name = "building_name") val buildingName: String?,
        @Json(name = "zone_no") val zoneNo: String?
    )

    @JsonClass(generateAdapter = true)
    data class Address(
        @Json(name = "address_name") val addressName: String,
        @Json(name = "region_1depth_name") val region1depthName: String,
        @Json(name = "region_2depth_name") val region2depthName: String,
        @Json(name = "region_3depth_name") val region3depthName: String?,
        @Json(name = "main_address_no") val mainAddressNo: String?,
        @Json(name = "sub_address_no") val subAddressNo: String?,
        @Json(name = "zip_code") val zipCode: String?
    )

    fun toLocation(latitude: Double, longitude: Double): Location {
        // 도로명 주소가 있으면 도로명 주소를, 없으면 지번 주소를 사용
        val addressName = documents.firstOrNull()?.roadAddress?.addressName
            ?: documents.firstOrNull()?.address?.addressName
            ?: "Unknown Location"
        return Location(latitude, longitude, addressName)
    }
}

