package com.aiku.presentation.ui.screen.schedule

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aiku.core.R
import com.aiku.core.theme.Body2_Medium
import com.aiku.core.theme.Headline_3G
import com.aiku.core.theme.Subtitle3_Medium
import com.aiku.core.theme.Subtitle3_SemiBold
import com.aiku.presentation.theme.Gray02
import com.aiku.presentation.theme.Gray03
import com.aiku.presentation.theme.Gray04
import com.aiku.presentation.theme.Gray05
import com.aiku.presentation.theme.Green5
import com.aiku.presentation.theme.ScreenBottomPadding
import com.aiku.presentation.theme.ScreenHorizontalPadding
import com.aiku.presentation.theme.Typo
import com.aiku.presentation.ui.component.button.FullWidthButton
import com.aiku.presentation.ui.component.textfield.BottomLinedTextField
import com.aiku.presentation.ui.screen.schedule.viewmodel.CreateScheduleViewModel
import com.aiku.presentation.util.to12TimeFormat
import com.aiku.presentation.util.toDefaultDateFormat
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateScheduleScreen(
    modifier: Modifier = Modifier,
    groupId: Long,
    onNavigateToSearchPlacesByKeywordScreen: () -> Unit,
    createScheduleViewModel: CreateScheduleViewModel
) {
    Log.d("ViewModelInstance", "SearchPlaceByMapScreen: ${createScheduleViewModel.hashCode()}")
    var showSelectDateDialog by remember { mutableStateOf(false) }
    var showSelectTimeDialog by remember { mutableStateOf(false) }

    val scheduleNameInput by createScheduleViewModel.scheduleNameInput.collectAsStateWithLifecycle()
    val scheduleDateInput by createScheduleViewModel.scheduleDateInput.collectAsStateWithLifecycle()
    val scheduleTimeInput by createScheduleViewModel.scheduleTimeInput.collectAsStateWithLifecycle()
    val scheduleLocation by createScheduleViewModel.scheduleLocation.collectAsStateWithLifecycle()
    val isButtonEnabled by createScheduleViewModel.isBtnEnabled.collectAsStateWithLifecycle()


    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("그룹1") } //TODO : 그룹이름으로 변경
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(top = 60.dp, bottom = ScreenBottomPadding)
                .padding(horizontal = ScreenHorizontalPadding)
        ) {

            /** 약속 이름 */
            Text(
                text = stringResource(id = R.string.schedule_name),
                style = Headline_3G,
                color = Typo
            )

            BottomLinedTextField(
                value = scheduleNameInput,
                onValueChange = createScheduleViewModel::onScheduleNameInputChanged,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                maxLines = 1,
                showLengthIndicator = true,
                showLabel = true,
                maxLength = CreateScheduleViewModel.MAX_SCHEDULE_NAME_LENGTH,
                placeholder = stringResource(id = R.string.schedule_name_setup_placeholder),
                label = stringResource(id = R.string.schedule_name_setup_label),
                textStyle = Subtitle3_Medium
            )

            /** 약속 시간 */
            Text(
                modifier = Modifier.padding(top = 60.dp),
                text = stringResource(id = R.string.schedule_datetime),
                style = Headline_3G,
                color = Typo
            )

            Row(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .fillMaxWidth()
                    .border(width = 1.dp, color = Gray05, shape = RoundedCornerShape(8.dp))
                    .padding(9.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(36.dp)
                        .drawBehind {
                            val strokeWidth = 1.dp.toPx()
                            drawLine(
                                color = Gray05,
                                start = Offset(size.width, 0f),
                                end = Offset(size.width, size.height),
                                strokeWidth = strokeWidth
                            )
                        }
                        .clickable { showSelectDateDialog = true }
                        .padding(vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    if (scheduleDateInput == null) {
                        Icon(
                            modifier = Modifier.size(20.dp),
                            painter = painterResource(id = R.drawable.ic_calendar),
                            tint = Gray03,
                            contentDescription = "날짜"
                        )
                    } else {
                        Text(
                            text = scheduleDateInput!!.atStartOfDay()
                                .toDefaultDateFormat(withDayOfWeek = false),
                            style = Body2_Medium,
                            color = Typo
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { showSelectTimeDialog = true }
                        .padding(vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    if (scheduleTimeInput == null) {
                        Icon(
                            modifier = Modifier.size(20.dp),
                            painter = painterResource(id = R.drawable.btm_nav_schedule),
                            tint = Gray03,
                            contentDescription = "시간"
                        )
                    } else {
                        Text(
                            text = scheduleTimeInput!!.atDate(LocalDate.now()).to12TimeFormat(),
                            style = Body2_Medium,
                            color = Typo
                        )
                    }
                }
            }


            /** 약속 장소 */
            Text(
                modifier = Modifier.padding(top = 60.dp),
                text = stringResource(id = R.string.schedule_location),
                style = Headline_3G,
                color = Typo
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp)
                    .clickable { onNavigateToSearchPlacesByKeywordScreen() }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = scheduleLocation?.placeName ?: stringResource(id = R.string.schedule_location_setup_placeholder),
                        style = Subtitle3_Medium,
                        color = if (scheduleLocation != null) Typo else Gray03
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Icon(
                        modifier = Modifier.size(22.dp),
                        painter = painterResource(id = R.drawable.ic_search),
                        tint = Gray04,
                        contentDescription = "검색"
                    )
                }

                HorizontalDivider(
                    modifier = Modifier.padding(top = 8.dp),
                    color = Gray03,
                    thickness = 1.dp
                )
            }




            Spacer(modifier = Modifier.weight(1f))

            FullWidthButton(
                modifier = Modifier.padding(top = 57.dp),
                enabled = isButtonEnabled,
                background = ButtonDefaults.buttonColors(
                    containerColor = Green5,
                    disabledContainerColor = Gray02
                ),
                onClick = { createScheduleViewModel.createSchedule(groupId = groupId) },
                content = {
                    Text(
                        text = stringResource(id = R.string.create),
                        style = Subtitle3_SemiBold,
                        color = Color.White
                    )
                }
            )
        }

        /** 날짜 선택 */
        if (showSelectDateDialog) {
            SelectDateDialog(
                onDateSelected = { selectedDate ->
                    createScheduleViewModel.updateScheduleDate(selectedDate)
                    showSelectDateDialog = false
                    showSelectTimeDialog = true
                },
                onDismissRequest = { showSelectDateDialog = false }
            )
        }

        /** 시간 선택 */
        if (showSelectTimeDialog) {
            SelectTimeDialog(
                onTimeSelected = { selectedTime ->
                    createScheduleViewModel.updateScheduleTime(selectedTime)
                    showSelectTimeDialog = false
                },
                onDismissRequest = { showSelectTimeDialog = false }
            )
        }
    }
}