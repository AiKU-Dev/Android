package com.aiku.presentation.ui.screen.home.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.aiku.core.R
import com.aiku.core.theme.Body2
import com.aiku.core.theme.Subtitle3_Bold
import com.aiku.presentation.state.group.GroupOverviewPaginationState
import com.aiku.presentation.theme.ColorStripCardStartPadding

@Composable
fun GroupCard(
    modifier: Modifier = Modifier,
    stripColor: Color,
    group: GroupOverviewPaginationState.GroupOverviewState,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier
            .shadow(
                elevation = 6.dp,  // 그림자 높이
                shape = RoundedCornerShape(0.dp, 16.dp, 16.dp, 0.dp),
                ambientColor = Color.Gray.copy(alpha = 0.8f),  // 그림자 색상 (투명도 조절)
                spotColor = Color.Black.copy(alpha = 0.4f)    // 좀 더 부드러운 그림자 색상
            ),
        shape = RoundedCornerShape(0.dp, 16.dp, 16.dp, 0.dp),
        onClick = { onClick() },
        colors = CardDefaults.cardColors().copy(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .drawBehind {
                    drawRect(
                        color = stripColor,
                        topLeft = Offset(0f, 0f),
                        size = Size(24f, size.height)
                    )
                }
                .padding(vertical = 16.dp, horizontal = ColorStripCardStartPadding),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = group.name,
                    style = Subtitle3_Bold
                )
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = group.lastScheduleTime.toString(),
                    style = Body2
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Row {
                Image(
                    modifier = Modifier.size(58.dp),
                    painter = painterResource(id = R.drawable.char_head_nohair),
                    contentDescription = stringResource(id = R.string.character_nohair)
                )
                Text(
                    modifier = Modifier.padding(top = 10.dp, start = 4.dp),
                    text = group.memberSize.toString(),
                    style = Subtitle3_Bold
                )
            }
        }
    }
}