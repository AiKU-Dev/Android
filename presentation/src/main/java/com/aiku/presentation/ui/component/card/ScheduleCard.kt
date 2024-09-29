package com.aiku.presentation.ui.component.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aiku.core.R
import com.aiku.core.theme.Caption1
import com.aiku.core.theme.Subtitle3
import com.aiku.domain.model.schedule.type.ScheduleStatus
import com.aiku.presentation.state.schedule.GroupScheduleOverviewState
import com.aiku.presentation.state.schedule.LocationState
import com.aiku.presentation.theme.Gray02
import com.aiku.presentation.theme.Gray03
import com.aiku.presentation.theme.Green5
import com.aiku.presentation.theme.Purple5
import com.aiku.presentation.ui.component.chip.ScheduleStatusChip
import com.aiku.presentation.util.to12TimeFormat
import com.aiku.presentation.util.toDefaultDateFormat
import java.time.LocalDateTime

@Composable
fun ScheduleCard(
    modifier: Modifier = Modifier,
    schedule: GroupScheduleOverviewState
) {
    var cardColor by remember {
        mutableStateOf(Purple5)
    }
    Card(
        modifier = modifier.fillMaxWidth().padding(horizontal = 2.dp).shadow(
            elevation = 2.dp,
            shape = RoundedCornerShape(0.dp,16.dp,16.dp,0.dp),
            ambientColor = Color.Black.copy(alpha = 0.1f)
        ),
        shape = RoundedCornerShape(0.dp,16.dp,16.dp,0.dp),
        onClick = { /*TODO*/ },
        colors = CardDefaults.cardColors().copy(
            containerColor = Color.White
        ),
        //elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .drawBehind {
                    drawRect(
                        color = cardColor,
                        topLeft = Offset(0f, 0f),
                        size = Size(24f, size.height)
                    )
                }
                .padding(top = 13.dp, start = 24.dp, end = 12.dp),
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = schedule.name, style = Subtitle3)
                Spacer(modifier = Modifier.weight(1f))
                ScheduleStatusChip(status = schedule.status)
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(painter = painterResource(id = R.drawable.ic_location), contentDescription = stringResource(
                    id = R.string.location_content_description)
                )
                Text(modifier = Modifier.padding(start = 4.dp), text = schedule.location.name, style = Caption1)
            }

            HorizontalDivider(thickness = 1.dp, color = Gray02, modifier = Modifier.padding(top = 20.dp))

            Row(modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .padding(top = 10.dp, bottom = 10.dp)
            ) {
                Text(
                    text = schedule.time.toDefaultDateFormat(true),    // TODO : Date
                )
                VerticalDivider(thickness = 1.dp, color = Color.Black, modifier = Modifier.padding(horizontal = 8.dp))
                Text(text = schedule.time.to12TimeFormat())
            }
        }
    }

    LaunchedEffect(key1 = schedule) {
        cardColor = when(schedule.status) {
            ScheduleStatus.RUN -> Green5
            ScheduleStatus.WAIT -> Purple5
            ScheduleStatus.TERM -> Gray03
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ScheduleRunningCardPreview() {
    ScheduleCard(
        schedule = GroupScheduleOverviewState(
            id = 1,
            name = "즐거운 약속^^",
            location = LocationState(.0, .0,"홍대 입구역 1번 출구"),
            time = LocalDateTime.now(),
            status = ScheduleStatus.RUN,
            memberSize = 5,
            accept = true
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun ScheduleWaitingCardPreview() {
    ScheduleCard(
        schedule = GroupScheduleOverviewState(
            id = 1,
            name = "안즐거운 공부팟",
            location = LocationState(.0, .0,"건대 7번 출구"),
            time = LocalDateTime.now(),
            status = ScheduleStatus.WAIT,
            memberSize = 5,
            accept = true
        )
    )
}