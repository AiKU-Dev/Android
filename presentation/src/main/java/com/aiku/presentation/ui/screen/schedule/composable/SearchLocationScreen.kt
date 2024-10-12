package com.aiku.presentation.ui.screen.schedule.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aiku.presentation.ui.screen.schedule.viewmodel.ScheduleViewModel


@Composable
fun SearchLocationScreen(
    scheduleViewModel: ScheduleViewModel = hiltViewModel()
) {
    //val locationResults by scheduleViewModel.locationResults.collectAsState()

    // 검색 입력 필드
    TextField(
        value = "", // 사용자가 입력한 텍스트 상태
        onValueChange = { /*query -> scheduleViewModel.searchLocations(query)*/ },
        label = { Text("장소 검색") }
    )

    // 검색 결과 리스트
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 15.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
//        items(locationResults) { location ->
//            Text(
//                text = location.name,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .clickable { /*TODO : navigate to S*/ }
//
//            )
//        }
    }
}