package com.aiku.presentation.ui.screen.map.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aiku.core.R
import com.aiku.core.theme.Body1_SemiBold
import com.aiku.core.theme.Caption1_Bold
import com.aiku.presentation.theme.ScreenHorizontalPadding
import com.aiku.presentation.theme.Typo

@Composable
fun OngoingScheduleMemberListItem(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = ScreenHorizontalPadding),
        verticalAlignment = Alignment.CenterVertically
    ) {
//        ProfileIcon(
//            modifier = Modifier
//                .padding(horizontal = 20.dp),
//            showClickRipple = false,
//            profile = MemberState( //TODO : MemberState로 가공
//                id = 1,
//                nickname = "nickname",
//                profile = , //TODO : ProfileState
//                point = 0
//            )
//        )

        Column {
            Text(
                text = "닉네임 여섯자", //TODO : 닉네임
                style = Body1_SemiBold,
                color = Typo
            )
            Text(
                text = "멤버",
                style = Caption1_Bold,
                color = Color.Black //TODO : 도착유무(isArrive)에 따라 색상 변경
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            modifier = Modifier.size(26.dp),
            painter = painterResource(id = R.drawable.btn_emoji),
            contentDescription = "이모지"
        )

        Icon(
            modifier = Modifier
                .padding(start = 23.dp)
                .size(26.dp),
            painter = painterResource(id = R.drawable.btn_racing),
            contentDescription = "레이싱"
        )

    }
}

@Preview(showBackground = true)
@Composable
private fun OngoingScheduleMemberListItemPreview() {
    OngoingScheduleMemberListItem()
}