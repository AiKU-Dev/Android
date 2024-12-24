package com.aiku.presentation.ui.screen.map.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aiku.core.R
import com.aiku.core.theme.Caption1
import com.aiku.core.theme.Caption1_SemiBold
import com.aiku.core.theme.Subtitle2
import com.aiku.core.theme.Subtitle_3G
import com.aiku.presentation.theme.Green5
import com.aiku.presentation.theme.RED1
import com.aiku.presentation.theme.Typo
import com.aiku.presentation.ui.component.dialog.MinimalDialog

@Composable
fun RacingResponseDialog(
    modifier: Modifier = Modifier
) {
    MinimalDialog(
        onDismissRequest = { /*TODO*/ }
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                text = "닉네임여섯자 님의 레이싱 신청", //TODO : 레이싱 신청자 이름으로 변경
                style = Subtitle2,
                color = Typo,
                textAlign = TextAlign.Center
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "상대보다 늦으면 ", //TODO : 레이싱 신청자 이름으로 변경
                    style = Caption1,
                    color = Typo
                )

                Text(
                    text = "10아쿠", //TODO : 실제 아쿠로 변경
                    style = Caption1_SemiBold,
                    color = Typo
                )

                Text(
                    text = "를 뺏기게 됩니다.",
                    style = Caption1,
                    color = Typo
                )
            }

            /** 거절, 수락 버튼 */
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center

            ) {
                Column(
                    modifier = Modifier
                        .size(108.dp, 132.dp)
                        .background(Green5, RoundedCornerShape(10.dp))
                        .clickable {  } //TODO : 거절
                    ,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        modifier = Modifier.size(58.dp, 66.dp),
                        painter = painterResource(id = R.drawable.ic_arrow_right), //TODO : 이미지 변경
                        contentDescription = "거절"
                    )
                    Text(
                        modifier = Modifier.padding(top = 20.dp),
                        text = "거절",
                        style = Subtitle_3G,
                        color = Color.White
                    )
                }

                Column(
                    modifier = Modifier
                        .padding(start = 24.dp)
                        .background(Green5, RoundedCornerShape(10.dp))
                        .size(108.dp, 132.dp)
                        .clickable {  } //TODO : 수락
                    ,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        modifier = Modifier.size(75.dp, 66.dp),
                        painter = painterResource(id = R.drawable.ic_arrow_right), //TODO : 이미지 변경
                        contentDescription = "수락"
                    )
                    Text(
                        modifier = Modifier.padding(top = 20.dp),
                        text = "수락",
                        style = Subtitle_3G,
                        color = Color.White
                    )
                }

            }

            /** 타이머 */
            Row(
                modifier = Modifier.padding(top = 28.dp, bottom = 16.dp)
            ) {
                Text(
                    text = "30초", //TODO : 비동기 Timer
                    style = Caption1,
                    color = RED1
                )

                Text(
                    text = "가 지나면 자동으로 레이싱이 취소됩니다.",
                    style = Caption1,
                    color = Typo
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RacingResponseDialogPreview() {
    RacingResponseDialog(
        modifier = Modifier
    )
}