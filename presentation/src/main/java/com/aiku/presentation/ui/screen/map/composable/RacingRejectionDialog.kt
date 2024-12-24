package com.aiku.presentation.ui.screen.map.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aiku.core.R
import com.aiku.core.theme.Caption1
import com.aiku.core.theme.Subtitle2
import com.aiku.presentation.theme.Typo
import com.aiku.presentation.ui.component.dialog.MinimalDialog

@Composable
fun RacingRejectionDialog(
    message : String,
    onDismissRequest: () -> Unit
) {
    MinimalDialog(
        onDismissRequest = { /*TODO*/ }
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 24.dp, bottom = 36.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "레이싱 거절", //TODO : 레이싱 신청자 이름으로 변경
                style = Subtitle2,
                color = Typo,
                textAlign = TextAlign.Center
            )

            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = message,
                style = Caption1,
                color = Typo
            )

            Icon(
                modifier = Modifier
                    .padding(top = 23.dp)
                    .size(58.dp, 66.dp),
                painter = painterResource(id = R.drawable.ic_arrow_right), //TODO : 이미지 변경
                contentDescription = "레이싱 거절"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RacingRejectionDialogPreview() {

    Column {
        RacingRejectionDialog(
            message = "닉네임여섯자 님의 레이싱 신청을 거절했습니다!",
            onDismissRequest = { /* 다이얼로그 닫기 */ }
        )

        RacingRejectionDialog(
            message = "30초가 지나서 자동으로 레이싱이 거절되었습니다!",
            onDismissRequest = { /* 다이얼로그 닫기 */ }
        )
    }

}