package com.aiku.presentation.ui.screen.schedule.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text2.input.TextFieldLineLimits
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aiku.core.R
import com.aiku.core.theme.Headline_3G
import com.aiku.core.theme.Subtitle3_SemiBold
import com.aiku.presentation.theme.Gray02
import com.aiku.presentation.theme.Gray05
import com.aiku.presentation.theme.Green5
import com.aiku.presentation.theme.ScreenHorizontalPadding
import com.aiku.presentation.theme.Typo
import com.aiku.presentation.ui.component.button.FullWidthButton
import com.aiku.presentation.ui.component.textfield.BottomLinedTextField
import com.aiku.presentation.ui.screen.schedule.viewmodel.ScheduleViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CreateScheduleScreen(
    scheduleViewModel: ScheduleViewModel = hiltViewModel()
) {
    val scheduleNameInput by scheduleViewModel.scheduleNameInput.collectAsStateWithLifecycle()
    val scheduleDateInput by scheduleViewModel.scheduleDateInput.collectAsStateWithLifecycle()
    val scheduleLocationInput by scheduleViewModel.scheduleLocationInput.collectAsStateWithLifecycle()
    val isButtonEnabled by scheduleViewModel.isBtnEnabled.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = ScreenHorizontalPadding)
    ) {
        Text(
            modifier = Modifier.padding(top = 116.dp),
            text = stringResource(id = R.string.schedule_name),
            style = Headline_3G,
            color = Typo
        )

        BottomLinedTextField(
            value = scheduleNameInput, //TODO : 수정
            onValueChange = scheduleViewModel::onScheduleNameInputChanged,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 38.dp),
            lineLimits = TextFieldLineLimits.SingleLine,
            showLengthIndicator = true,
            maxLength = ScheduleViewModel.MAX_SCHEDULENAME_LENGTH,
            placeholder = stringResource(id = R.string.schedule_name_setup_placeholder),
            label = stringResource(id = R.string.schedule_name_setup_label),
        )

        Text(
            modifier = Modifier.padding(top = 116.dp),
            text = stringResource(id = R.string.schedule_time),
            style = Headline_3G,
            color = Typo
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    color = Gray05,
                    width = 1.dp,
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            Box(
                modifier = Modifier.weight(0.5f),
                contentAlignment = Alignment.Center
            ){
                Icon(
                    modifier = Modifier
                        .padding(vertical = 17.dp)
                        .size(20.dp),
                    painter = painterResource(id = R.drawable.ic_calendar),
                    contentDescription = stringResource(id = R.string.schedule_calendar)
                )
            }
            Box(
                modifier = Modifier.weight(0.5f),
                contentAlignment = Alignment.Center
            ){
                Icon(
                    modifier = Modifier
                        .padding(vertical = 17.dp)
                        .size(20.dp),
                    painter = painterResource(id = R.drawable.ic_clock),
                    contentDescription = stringResource(id = R.string.schedule_clock)
                )
            }
        }

        Text(
            modifier = Modifier.padding(top = 116.dp),
            text = stringResource(id = R.string.schedule_location),
            style = Headline_3G,
            color = Typo
        )

        BottomLinedTextField(
            value = scheduleLocationInput,
            onValueChange = scheduleViewModel::onScheduleNameInputChanged,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 38.dp),
            lineLimits = TextFieldLineLimits.SingleLine,
            showLengthIndicator = true,
            maxLength = ScheduleViewModel.MAX_SCHEDULENAME_LENGTH,
            placeholder = stringResource(id = R.string.schedule_location_setup_placeholder)
        )

        FullWidthButton(
            enabled = isButtonEnabled,
            background = ButtonDefaults.buttonColors(
                containerColor = Green5,
                disabledContainerColor = Gray02
            ),
//            onClick = { scheduleViewModel.createSchedule() },
            content = {
                Text(
                    text = stringResource(id = R.string.create),
                    style = Subtitle3_SemiBold,
                    color = Color.White
                )
            }
        )
    }
}