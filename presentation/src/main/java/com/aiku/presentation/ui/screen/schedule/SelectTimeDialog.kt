package com.aiku.presentation.ui.screen.schedule

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aiku.core.R
import com.aiku.core.theme.Subtitle3_SemiBold
import com.aiku.presentation.theme.Gray02
import com.aiku.presentation.theme.Green5
import com.aiku.presentation.ui.component.button.FullWidthButton
import com.aiku.presentation.ui.component.dialog.MinimalDialog
import com.aiku.presentation.ui.component.layout.LazyColumnWithAutoCenter
import java.time.LocalTime

@Composable
fun SelectTimeDialog(
    onTimeSelected: (LocalTime) -> Unit,
    onDismissRequest: () -> Unit
) {
    val currentTime = LocalTime.now()

    var selectedPeriod by remember { mutableStateOf(if (currentTime.hour < 12) "오전" else "오후") }
    var selectedHour by remember { mutableStateOf(if (currentTime.hour % 12 == 0) 12 else currentTime.hour % 12) }
    var selectedMinute by remember { mutableStateOf(currentTime.minute) }

    val periodState = rememberLazyListState(initialFirstVisibleItemIndex = if (selectedPeriod == "오전") 0 else 1)
    val hourState = rememberLazyListState(initialFirstVisibleItemIndex = selectedHour - 1)
    val minuteState = rememberLazyListState(initialFirstVisibleItemIndex = selectedMinute)

    MinimalDialog(onDismissRequest = onDismissRequest) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 오전/오후 선택
                LazyColumnWithAutoCenter(
                    modifier = Modifier.weight(1f),
                    state = periodState,
                    items = listOf("오전", "오후"),
                    onValueChange = { selectedPeriod = it }
                )

                // 시 선택
                LazyColumnWithAutoCenter(
                    modifier = Modifier.weight(1f),
                    state = hourState,
                    items = (1..12).toList(),
                    onValueChange = { selectedHour = it }
                )

                // 분 선택
                LazyColumnWithAutoCenter(
                    modifier = Modifier.weight(1f),
                    state = minuteState,
                    items = (0..59).toList(),
                    onValueChange = { selectedMinute = it }
                )
            }

            FullWidthButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                background = ButtonDefaults.buttonColors(
                    containerColor = Green5,
                    disabledContainerColor = Gray02
                ),
                onClick = {
                    val hour24 = if (selectedPeriod == "오전") {
                        if (selectedHour == 12) 0 else selectedHour
                    } else {
                        if (selectedHour == 12) 12 else selectedHour + 12
                    }
                    onTimeSelected(LocalTime.of(hour24, selectedMinute))
                    onDismissRequest()
                },
                content = {
                    Text(
                        text = stringResource(id = R.string.ok),
                        style = Subtitle3_SemiBold,
                        color = Color.White
                    )
                }
            )
        }
    }

}

@Preview
@Composable
private fun SelectTimeDialogPreview() {
    SelectTimeDialog(
        onTimeSelected = {},
        onDismissRequest = {}
    )
}
