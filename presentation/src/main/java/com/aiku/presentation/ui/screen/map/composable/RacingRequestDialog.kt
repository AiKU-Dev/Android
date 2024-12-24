package com.aiku.presentation.ui.screen.map.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aiku.core.R
import com.aiku.core.theme.Body1_SemiBold
import com.aiku.core.theme.Caption1_Medium
import com.aiku.core.theme.Caption1_SemiBold
import com.aiku.core.theme.Subtitle1
import com.aiku.core.theme.Subtitle2
import com.aiku.presentation.theme.AkuYellow
import com.aiku.presentation.theme.CobaltBlue
import com.aiku.presentation.theme.Gray02
import com.aiku.presentation.theme.Gray04
import com.aiku.presentation.theme.Gray06
import com.aiku.presentation.theme.Typo
import com.aiku.presentation.ui.component.dialog.SingleButtonDialog
import com.aiku.presentation.ui.screen.map.viewmodel.RacingViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun RacingRequestDialog(
    modifier: Modifier = Modifier,
    racingViewModel: RacingViewModel = hiltViewModel()
) {
    val currentAku by racingViewModel.currentAku.collectAsState()

    SingleButtonDialog(
        onButtonClicked = {},
        onDismissRequest = {},
        buttonText = "신청하기",
        content = {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(top = 22.dp),
                    text = "1:1 레이싱",
                    style = Subtitle1,
                    color = Typo
                )

                Text(
                    modifier = Modifier.padding(top = 12.dp),
                    text = "상대보다 먼저 도착하면 아쿠를 뺏어올 수 있습니다.",
                    style = Caption1_Medium,
                    color = Typo
                )

                /** 본인 */
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
//                        ProfileIcon(
//                            modifier = Modifier
//                                .padding(horizontal = 20.dp),
//                            showClickRipple = false,
//                            profile = MemberState( //TODO : MemberState로 가공
//                                id = 1,
//                                nickname = "nickname",
//                                profile =, //TODO : ProfileState
//                                point = 0
//                            )
//                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(20.dp)
                                    .background(color = Gray06, shape = CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "나",
                                    color = Color.White,
                                    style = Caption1_SemiBold
                                )
                            }

                            Text(
                                modifier = Modifier.padding(start = 4.dp),
                                text = "사용자1", //TODO : 사용자이름으로 변경
                                color = Typo,
                                style = Caption1_SemiBold
                            )
                        }

                        Row(
                            modifier = Modifier.padding(top = 5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(16.dp)
                                    .background(color = AkuYellow, shape = CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "A",
                                    color = Color.White,
                                    style = Caption1_SemiBold
                                )
                            }

                            Text(
                                modifier = Modifier.padding(start = 4.dp),
                                text = "5000", //TODO : 사용자 아쿠로 변경
                                color = Typo,
                                style = Caption1_SemiBold
                            )
                        }

                    }

                    Text(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        text = "VS",
                        style = Body1_SemiBold,
                        color = Typo
                    )

                    /** 상대방 */
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
//                        ProfileIcon(
//                            modifier = Modifier
//                                .padding(horizontal = 20.dp),
//                            showClickRipple = false,
//                            profile = MemberState( //TODO : MemberState로 가공
//                                id = 1,
//                                nickname = "nickname",
//                                profile =, //TODO : ProfileState
//                                point = 0
//                            )
//                        )


                        Box(
                            modifier = Modifier
                                .height(20.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "사용자2", // TODO : 사용자 이름으로 변경
                                color = Typo,
                                style = Caption1_SemiBold
                            )
                        }


                        Row(
                            modifier = Modifier.padding(top = 5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(16.dp)
                                    .background(color = AkuYellow, shape = CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "A",
                                    color = Color.White,
                                    style = Caption1_SemiBold
                                )
                            }

                            Text(
                                modifier = Modifier.padding(start = 4.dp),
                                text = "5000", //TODO : 사용자 아쿠로 변경
                                color = Typo,
                                style = Caption1_SemiBold
                            )
                        }

                    }
                }


                /** 아쿠 금액 설정 */
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp)
                        .padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween

                ) {
                    IconButton(
                        enabled = racingViewModel.currentAku.value > 10,
                        onClick = { racingViewModel.decreaseAku() }
                    ) {
                        Icon(
                            modifier = Modifier.size(17.dp),
                            painter = painterResource(id = R.drawable.ic_arrow_left),
                            contentDescription = "-10 아쿠",
                            tint = if (racingViewModel.currentAku.value > 10) Gray04 else Gray02
                        )
                    }

                    Text(
                        text = "${currentAku}아쿠", //TODO : 동적으로 아쿠 변경
                        style = Subtitle2,
                        color = CobaltBlue,
                    )

                    IconButton(onClick = { racingViewModel.increaseAku() }) {
                        Icon(
                            modifier = Modifier.size(17.dp),
                            painter = painterResource(id = R.drawable.ic_arrow_right),
                            contentDescription = "+10 아쿠",
                            tint = Color.Gray //TODO : 내 보유 아쿠를 넘어가면 비활성화 후, 색상변경
                        )
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun RacingRequestDialogPreview() {
    RacingRequestDialog(
        modifier = Modifier,
        racingViewModel = RacingViewModel()
    )
}