package com.aiku.presentation.ui.screen.map.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.aiku.core.R
import com.aiku.core.theme.Caption1
import com.aiku.core.theme.Caption1_SemiBold
import com.aiku.core.theme.Subtitle3_SemiBold
import com.aiku.core.theme.Subtitle_3G
import com.aiku.presentation.theme.CobaltBlue
import com.aiku.presentation.theme.Gray02
import com.aiku.presentation.theme.Gray04
import com.aiku.presentation.theme.ScreenHorizontalPadding
import com.aiku.presentation.theme.Typo
import com.aiku.presentation.ui.component.topbar.DefaultTopAppBar
import com.aiku.presentation.ui.group.GroupTopAppBar
import com.aiku.presentation.ui.screen.schedule.PlaceListItem
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.MapLifeCycleCallback
import com.kakao.vectormap.MapView
import com.kakao.vectormap.camera.CameraUpdateFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemberBottomSheet(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val mapView = remember { MapView(context) }

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.PartiallyExpanded
        )
    )

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetDragHandle = { // 드래그 핸들 커스텀
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 18.dp)
                    .background(color = Color.Transparent),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "멤버",
                    style = Subtitle_3G,
                    color = Color.Black
                )
            }
        },
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        sheetContainerColor = Color.White,
        sheetContent = {
            /** bottom sheet 콘텐츠 (멤버 리스트) */
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                //TODO : viewmodel ui state success 에 넣기 (실제 DTO로 변경)
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 18.dp)
                ) {
//                    items(searchResults.size) { index ->
//                        OngoingScheduleMemberListItem()
//
//                        // 마지막 아이템이 아닐 경우 Divider 추가
//                        if (index < searchResults.size - 1) {
//                            HorizontalDivider(
//                                thickness = 1.dp,
//                                color = Gray02,
//                                modifier = Modifier.padding(top = 15.dp)
//                            )
//                        }
//                    }
                }
            }
        }
    ) {
        /** 메인 화면 콘텐츠 */
        Scaffold(
            modifier = modifier
                .fillMaxSize(),
            topBar = {
                GroupTopAppBar(
                    title = "그룹이름", //TODO : 그룹이름으로 변경
                    actions = {
                    IconButton(onClick = { /* BACK */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_exit),
                            contentDescription = stringResource(
                                id = R.string.group_more_icon_content_description
                            )
                        )
                    }
                })
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding)
            ) {

                /** 약속 정보 */
                OngoingScheduleInfo(
                    modifier = Modifier.padding(horizontal = ScreenHorizontalPadding)
                )

                /** 지도 */
                AndroidView(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 23.dp),
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
                                            CameraUpdateFactory.newCenterPosition( //TODO : 약속장소로 카메라 이동
                                                LatLng.from(37.556752, 126.923851)
                                            )
                                        kakaoMap.moveCamera(cameraUpdate)

                                        //TODO : 약속장소 라벨
                                        //TODO : 멤버들 라벨 띄우기
                                    }
                                },
                            )
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MemberBottomSheetPreview() {
    MemberBottomSheet()
}

