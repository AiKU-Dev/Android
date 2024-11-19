package com.aiku.presentation.state.schedule

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.aiku.domain.model.schedule.Place
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
@Immutable
data class PlaceState(
    val placeName: String,
    val addressName: String,
    val latitude: Double,
    val longitude: Double
) : Parcelable

fun Place.toPlaceState() = PlaceState(placeName, addressName, latitude, longitude)