package com.aiku.presentation.ui.screen.schedule.composable

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.aiku.core.R
import com.aiku.core.theme.Body1
import com.aiku.presentation.ui.component.button.FullWidthButton
import com.aiku.presentation.ui.screen.schedule.viewmodel.CreateScheduleViewModel
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.MapLifeCycleCallback
import com.kakao.vectormap.MapView
import com.kakao.vectormap.camera.CameraAnimation
import com.kakao.vectormap.camera.CameraUpdateFactory
import com.kakao.vectormap.label.LabelOptions
import com.kakao.vectormap.label.LabelStyle
import com.kakao.vectormap.label.LabelStyles
import com.kakao.vectormap.label.LabelTransition
import com.kakao.vectormap.label.Transition


@Composable
fun ShowLocationOnMapScreen(
    createScheduleViewModel: CreateScheduleViewModel = hiltViewModel()
) {
    val selectedLocation by createScheduleViewModel.selectedLocation.collectAsState()
    selectedLocation?.let { Log.d("kakao", it.name) }

    Column {

        KakaoMapView(
            latitude = selectedLocation?.latitude ?: 37.5405,
            longitude = selectedLocation?.longitude ?: 127.0701,
            onCameraMoved = { lat, lng ->
                createScheduleViewModel.updateLocation(lat, lng)
            }
        )

    }
}

@Composable
fun KakaoMapView(
    modifier: Modifier = Modifier,
    latitude: Double,
    longitude: Double,
    onCameraMoved: (Double, Double) -> Unit
) {

    val context = LocalContext.current

    Box(
        contentAlignment = Alignment.Center
    ) {

        AndroidView(
            factory = { MapView(context) },
            update = { mapView ->
                mapView.start(object : MapLifeCycleCallback() {
                    override fun onMapDestroy() {
                        Log.d("KakaoMap", "Map destroyed")
                    }

                    override fun onMapError(error: Exception) {
                        Log.d("KakaoMap", error.toString())
                    }
                }, object : KakaoMapReadyCallback() {
                    override fun onMapReady(kakaoMap: KakaoMap) {

                        val pos = LatLng.from(latitude, longitude)
                        kakaoMap.moveCamera(
                            CameraUpdateFactory.newCenterPosition(pos, 18),
                            CameraAnimation.from(300)
                        )

                        kakaoMap.setOnCameraMoveEndListener { _, position, gestureType ->
                            onCameraMoved(position.position.latitude, position.position.longitude)
                        }

                    }
                })
            }
        )

        Image(
            modifier = Modifier.size(78.dp, 80.dp),
            painter = painterResource(id = R.drawable.img_marker),
            contentDescription = "marker"
        )
    }

}




