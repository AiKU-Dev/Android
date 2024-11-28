package com.aiku.presentation.ui.screen.schedule

import android.util.Log
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aiku.core.R
import com.aiku.core.theme.Body2_SemiBold
import com.aiku.core.theme.Caption1
import com.aiku.core.theme.Subtitle3_SemiBold
import com.aiku.presentation.theme.Gray02
import com.aiku.presentation.theme.Gray03
import com.aiku.presentation.theme.Gray04
import com.aiku.presentation.theme.Green5
import com.aiku.presentation.theme.ScreenBottomPadding
import com.aiku.presentation.theme.ScreenHorizontalPadding
import com.aiku.presentation.theme.Typo
import com.aiku.presentation.ui.component.button.FullWidthButton
import com.aiku.presentation.ui.screen.schedule.viewmodel.CreateScheduleViewModel
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.MapLifeCycleCallback
import com.kakao.vectormap.MapView
import com.kakao.vectormap.camera.CameraUpdateFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchPlaceByMapScreen(
    modifier: Modifier = Modifier,
    onNavigateToSearchPlacesByKeywordScreen : () -> Unit,
    onNavigateToCreateScheduleScreen : () -> Unit,
    createScheduleViewModel: CreateScheduleViewModel
) {
    Log.d("ViewModelInstance", "SearchPlaceByMapScreen: ${createScheduleViewModel.hashCode()}")
    val scheduleLocation by createScheduleViewModel.scheduleLocation.collectAsStateWithLifecycle()

    val context = LocalContext.current
    val mapView = remember { MapView(context) }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("장소 검색") } //TODO : 추후 디자인 반영
            )
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 40.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = ScreenHorizontalPadding)
                        .padding(bottom = 9.dp)
                ) {

                    /** 장소 정보 */
                    Log.d("create schedule ui", scheduleLocation.toString())
                    scheduleLocation?.let {
                        Text(
                            text = it.placeName,
                            style = Body2_SemiBold,
                            color = Typo
                        )
                    }

                    Row(
                        modifier = Modifier.padding(top = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        scheduleLocation?.let {
                            Text(
                                text = it.addressName,
                                style = Caption1,
                                color = Gray03
                            )
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        Icon(
                            modifier = Modifier.size(22.dp),
                            painter = painterResource(id = R.drawable.ic_search),
                            tint = Gray04,
                            contentDescription = "검색"
                        )
                    }

                }

                /** 지도 */
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {

                    AndroidView(
                        modifier = modifier.fillMaxWidth(),
                        factory = {
                            mapView.apply {
                                mapView.start(
                                    object : MapLifeCycleCallback() {

                                        override fun onMapDestroy() {}

                                        override fun onMapError(exception: Exception?) {}
                                    },
                                    object : KakaoMapReadyCallback() {

                                        override fun onMapReady(kakaoMap: KakaoMap) {

                                            /** LatLng으로 카메라 이동 */
                                            val cameraUpdate =
                                                CameraUpdateFactory.newCenterPosition(
                                                    scheduleLocation?.let { it1 ->
                                                        LatLng.from(
                                                            it1.latitude,
                                                            it1.longitude
                                                        )
                                                    })
                                            kakaoMap.moveCamera(cameraUpdate)

                                        }

//                                    override fun getPosition(): LatLng {
//                                        // 현재 위치를 반환
//                                        return LatLng.from(locationY, locationX)
//                                    }
                                    },
                                )
                            }
                        },
                    )

                    Icon(
                        modifier = Modifier
                            .size(78.dp, 80.dp)
                            .offset(y = (-40).dp),
                        painter = painterResource(id = R.drawable.ic_mark),
                        contentDescription = "목적지"
                    )

                }

            }

            FullWidthButton(
                modifier = Modifier
                    .padding(bottom = ScreenBottomPadding)
                    .padding(horizontal = ScreenHorizontalPadding),
                background = ButtonDefaults.buttonColors(
                    containerColor = Green5,
                    disabledContainerColor = Gray02
                ),
                onClick = { onNavigateToCreateScheduleScreen() },
                content = {
                    Text(
                        text = stringResource(id = R.string.ok),
                        style = Subtitle3_SemiBold,
                        color = Color.White
                    )
                }
            )
        }
    }
}


