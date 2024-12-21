package com.aiku.presentation.ui.screen.map.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.aiku.core.theme.Caption1
import com.aiku.core.theme.Caption1_SemiBold
import com.aiku.core.theme.Subtitle3_SemiBold
import com.aiku.presentation.theme.CobaltBlue
import com.aiku.presentation.theme.Typo

@Composable
fun OngoingScheduleInfo(
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "약속 장소", //TODO : 약속장소로 변경
                style = Caption1,
                color = Typo
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "약속 시간", //TODO : 약속시간으로 변경
                style = Subtitle3_SemiBold,
                color = Typo
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        TextButton(
            shape = RoundedCornerShape(6.dp),
            colors = ButtonDefaults.textButtonColors(
                containerColor = CobaltBlue
            ),
            contentPadding = PaddingValues(10.dp),
            onClick = { /*TODO*/ }
        ) {
            Text(
                text = "진행 중인 레이싱 보기",
                style = Caption1_SemiBold,
                color = Color.White
            )
        }
    }
}