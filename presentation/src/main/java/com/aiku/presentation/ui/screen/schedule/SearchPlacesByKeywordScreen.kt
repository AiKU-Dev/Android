package com.aiku.presentation.ui.screen.schedule

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aiku.core.R
import com.aiku.core.theme.Body2_SemiBold
import com.aiku.core.theme.Caption1
import com.aiku.core.theme.Subtitle3_Medium
import com.aiku.presentation.theme.Gray03
import com.aiku.presentation.theme.Gray04
import com.aiku.presentation.theme.ScreenBottomPadding
import com.aiku.presentation.theme.ScreenHorizontalPadding
import com.aiku.presentation.theme.Typo
import com.aiku.presentation.ui.component.textfield.BottomLinedTextField
import com.aiku.presentation.ui.screen.schedule.viewmodel.CreateScheduleViewModel
import com.aiku.presentation.ui.screen.schedule.viewmodel.SearchPlacesByKeywordUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchPlacesByKeywordScreen(
    modifier: Modifier = Modifier,
    onNavigateToSearchPlaceByMapScreen: () -> Unit,
    createScheduleViewModel: CreateScheduleViewModel
) {

    Log.d("ViewModelInstance", "SearchPlaceByMapScreen: ${createScheduleViewModel.hashCode()}")
    val placeNameInput by createScheduleViewModel.placeNameInput.collectAsStateWithLifecycle()
    val searchResults by createScheduleViewModel.searchResults.collectAsStateWithLifecycle()
    val searchPlacesByKeywordUiState by createScheduleViewModel.searchPlacesByKeywordUiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("장소 검색") } //TODO : 추후 디자인 반영
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(top = 60.dp, bottom = ScreenBottomPadding)
                .padding(horizontal = ScreenHorizontalPadding)
        ) {

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Icon(
                    modifier = Modifier.size(22.dp),
                    painter = painterResource(id = R.drawable.ic_search),
                    tint = Gray04,
                    contentDescription = "검색"
                )

                BottomLinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = placeNameInput,
                    onValueChange = {
                        createScheduleViewModel.onPlaceNameChanged(it)
                    },
                    maxLines = 1,
                    showLengthIndicator = false,
                    showLabel = false,
                    placeholder = stringResource(id = R.string.schedule_location_setup_placeholder),
                    maxLength = CreateScheduleViewModel.MAX_SCHEDULE_NAME_LENGTH,
                    textStyle = Subtitle3_Medium
                )

            }

            when (searchPlacesByKeywordUiState) {
                SearchPlacesByKeywordUiState.Loading -> {}
                SearchPlacesByKeywordUiState.Error -> {}
                SearchPlacesByKeywordUiState.Success -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    ) {
                        items(searchResults.size) { index ->
                            PlaceListItem(
                                placeName = searchResults[index].placeName,
                                addressName = searchResults[index].addressName,
                                onClick = {
                                    createScheduleViewModel.updateScheduleLocation(searchResults[index])
                                    onNavigateToSearchPlaceByMapScreen()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PlaceListItem(
    placeName: String,
    addressName: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Icon(
            modifier = Modifier.size(20.dp, 22.dp),
            painter = painterResource(id = R.drawable.ic_location),
            tint = Gray03,
            contentDescription = stringResource(id = R.string.location_content_description)
        )

        Column(
            modifier = Modifier.padding(start = 12.dp, top = 2.dp)
        ) {
            Text(
                text = placeName,
                style = Body2_SemiBold,
                color = Typo
            )
            Text(
                modifier = Modifier.padding(8.dp),
                text = addressName,
                style = Caption1,
                color = Gray03
            )
        }

    }



}

