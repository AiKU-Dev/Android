package com.aiku.presentation.ui.screen.map.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aiku.core.theme.Body1_SemiBold
import com.aiku.core.theme.Body2_Medium
import com.aiku.core.theme.Caption1_SemiBold
import com.aiku.presentation.theme.AkuYellow
import com.aiku.presentation.theme.CobaltBlue
import com.aiku.presentation.theme.Gray02
import com.aiku.presentation.theme.Typo
import com.aiku.presentation.ui.component.shadow.defaultShadow

@Composable
fun OngoingRacingListItem(
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        /** 레이싱 정보 */
        RacingDisplay(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp) // 아쿠 표시를 겹치도록 패딩 추가
        )

        /** 아쿠 표시 */
        AkuDisplay(
            modifier = Modifier
                .align(Alignment.TopCenter) // 중앙 상단에 위치
        )
    }
}

@Composable
fun AkuDisplay(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(
                color = Color.White,
                shape = RoundedCornerShape(50)
            )
            .border(2.dp, Gray02, RoundedCornerShape(15.dp))
            .padding(horizontal = 25.dp, vertical = 7.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(18.dp)
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
                modifier = Modifier.padding(start = 6.dp),
                text = "50 아쿠", //TODO : 레이싱 아쿠로 변경
                color = CobaltBlue,
                style = Caption1_SemiBold
            )
        }
    }
}

@Composable
fun RacingDisplay(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .defaultShadow()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(top = 25.dp, bottom = 10.dp, start = 40.dp, end = 40.dp)
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // 첫 번째 사용자
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

            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = "사용자1", //TODO : 사용자이름으로 변경
                color = Typo,
                style = Body2_Medium
            )
        }

        // VS 텍스트
        Text(
            text = "VS",
            color = Color.Black,
            style = Body1_SemiBold
        )

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

            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = "사용자2", //TODO : 사용자이름으로 변경
                color = Typo,
                style = Body2_Medium
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun OngoingRacingListItemPreview() {
    OngoingRacingListItem()
}

