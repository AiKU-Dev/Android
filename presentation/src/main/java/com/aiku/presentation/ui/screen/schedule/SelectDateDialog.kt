package com.aiku.presentation.ui.screen.schedule

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.aiku.presentation.theme.Typo
import com.aiku.presentation.ui.component.button.FullWidthButton
import com.aiku.presentation.ui.component.dialog.MinimalDialog
import com.aiku.presentation.ui.component.layout.LazyColumnWithAutoCenter
import kotlinx.coroutines.launch
import java.time.LocalDate

@Composable
fun SelectDateDialog(
    onDateSelected: (LocalDate) -> Unit,
    onDismissRequest: () -> Unit
) {
    // 현재 연도, 월, 일을 초기값으로 설정
    val currentDate = LocalDate.now()
    var selectedYear by remember { mutableStateOf(currentDate.year) }
    var selectedMonth by remember { mutableStateOf(currentDate.monthValue) }
    var selectedDay by remember { mutableStateOf(currentDate.dayOfMonth) }

    // LazyColumn의 스크롤 상태 관리
    val yearState = rememberLazyListState(initialFirstVisibleItemIndex = 0) // 현재 연도가 맨 위로
    val monthState = rememberLazyListState(initialFirstVisibleItemIndex = currentDate.monthValue - 1)
    val dayState = rememberLazyListState(initialFirstVisibleItemIndex = currentDate.dayOfMonth - 1)

    MinimalDialog(onDismissRequest = onDismissRequest) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                /** 연도 선택 */
                LazyColumnWithAutoCenter(
                    modifier = Modifier.weight(1f),
                    state = yearState,
                    items = (currentDate.year..(currentDate.year + 25)).toList(),
                    onValueChange = { selectedYear = it }
                )

                /** 월 선택 */
                LazyColumnWithAutoCenter(
                    modifier = Modifier.weight(1f),
                    state = monthState,
                    items = (1..12).toList(),
                    onValueChange = {
                        selectedMonth = it
                        // 선택된 월에 따라 일자 범위 동적 업데이트
                        if (selectedDay > calculateDaysInMonth(selectedYear, selectedMonth)) {
                            selectedDay = calculateDaysInMonth(selectedYear, selectedMonth)
                        }
                    }
                )

                /** 일 선택 */
                LazyColumnWithAutoCenter(
                    modifier = Modifier.weight(1f),
                    state = dayState,
                    items = (1..calculateDaysInMonth(selectedYear, selectedMonth)).toList(),
                    onValueChange = { selectedDay = it }
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
                    onDateSelected(LocalDate.of(selectedYear, selectedMonth, selectedDay))
                    onDismissRequest()
                },
                content = {
                    Text(
                        text = stringResource(id = R.string.next),
                        style = Subtitle3_SemiBold,
                        color = Color.White
                    )
                }
            )
        }
    }
}

// 월별 최대 일수 계산
fun calculateDaysInMonth(year: Int, month: Int): Int {
    return LocalDate.of(year, month, 1).lengthOfMonth()
}

@Preview
@Composable
private fun SelectDateDialogPreview() {
    SelectDateDialog(
        onDateSelected = {},
        onDismissRequest = {}
    )
}
