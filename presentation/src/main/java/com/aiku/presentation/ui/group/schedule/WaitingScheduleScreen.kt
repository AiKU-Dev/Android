package com.aiku.presentation.ui.group.schedule

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.aiku.core.R
import com.aiku.core.theme.Body2
import com.aiku.core.theme.Caption1
import com.aiku.core.theme.Headline_2G
import com.aiku.domain.model.schedule.type.ScheduleStatus
import com.aiku.presentation.state.group.GroupState
import com.aiku.presentation.state.schedule.GroupScheduleOverviewState
import com.aiku.presentation.state.schedule.LocationState
import com.aiku.presentation.ui.component.graph.CircularTimeGraph
import com.aiku.presentation.ui.component.row.TimeIndicationRow
import com.aiku.presentation.ui.group.GroupTopAppBar
import com.aiku.presentation.ui.group.schedule.viewmodel.WaitingScheduleViewModel
import com.aiku.presentation.util.formatSecondsToHHMMSS
import com.aiku.presentation.util.getSecondsDifferenceFromNow
import java.time.LocalDateTime

@Composable
fun WaitingScheduleScreen(
    modifier: Modifier = Modifier,
    group: GroupState,
    schedule: GroupScheduleOverviewState,
    viewModel: WaitingScheduleViewModel = hiltViewModel(
        creationCallback = { factory: WaitingScheduleViewModel.Factory ->
            factory.create(group, schedule)
        }
    )
) {

    val secondsDiffFromNow = schedule.time.getSecondsDifferenceFromNow()

    Scaffold(
        modifier = modifier,
        topBar = {
            GroupTopAppBar(title = group.name, actions = {
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
            AssistChip(
                enabled = false,
                onClick = { }, label = {
                    Column {
                        TimeIndicationRow(
                            time = schedule.time,
                            textStyle = Caption1.copy(fontWeight = FontWeight.Bold)
                        )
                        Text(text = schedule.location.name, modifier = Modifier.padding(top = 6.dp))
                    }
                }, trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_calendar),
                        contentDescription = stringResource(
                            id = R.string.schedule_calendar_content_description
                        )
                    )
                })

            Box(
                modifier = Modifier.padding(top = 36.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularTimeGraph(
                    totalTime = 1800, elapsedTime = secondsDiffFromNow
                )
                Column {
                    Text(
                        text = stringResource(R.string.remaining_time),
                        style = Body2,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(
                        modifier = Modifier.padding(top = 10.dp),
                        text = secondsDiffFromNow.formatSecondsToHHMMSS(),
                        style = Headline_2G
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WaitingScheduleScreenPreview() {
    WaitingScheduleScreen(
        modifier = Modifier,
        group = GroupState(
            id = 1,
            name = "놀자팟",
            members = listOf(),
        ),
        schedule = GroupScheduleOverviewState(
            id = 1,
            time = LocalDateTime.now(),
            status = ScheduleStatus.WAIT,
            accept = true,
            memberSize = 0,
            name = "한강 번개",
            location = LocationState(
                latitude = 0.0,
                longitude = 0.0,
                name = "한강어딘가"
            )
        )
    )
}