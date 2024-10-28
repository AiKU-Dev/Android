package com.aiku.presentation.ui.screen.home.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.aiku.core.R
import com.aiku.core.theme.Body2
import com.aiku.core.theme.Subtitle_2G
import com.aiku.core.theme.Subtitle_4G
import com.aiku.presentation.state.group.GroupOverviewPaginationState
import com.aiku.presentation.state.schedule.UserScheduleOverviewState
import com.aiku.presentation.theme.Gray02
import com.aiku.presentation.theme.Green5
import com.aiku.presentation.theme.Purple5
import com.aiku.presentation.theme.ScaffoldTopContentSpacing
import com.aiku.presentation.theme.ScreenHorizontalPadding
import com.aiku.presentation.theme.Typo
import com.aiku.presentation.theme.Yellow5
import com.aiku.presentation.ui.component.button.FloatingActionPlusButton
import com.aiku.presentation.ui.screen.home.viewmodel.HomeViewModel
import com.aiku.presentation.ui.screen.home.viewmodel.UserScheduleUiState
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold(
        topBar = { Text("Home") },
        floatingActionButton = { FloatingActionPlusButton(onClick = { /* TODO : navigate to make group */ }) }
    ) { innerPadding ->
        HomeContent(innerPadding)
    }
}

@Composable
fun HomeContent(
    innerPadding: PaddingValues,
    homeViewModel: HomeViewModel = hiltViewModel()) {

    val userScheduleState = homeViewModel.userScheduleUiState.collectAsState()

    // TODO : GroupViewModel에서 불러오기
    val items = listOf(
        "그룹1", "그룹2", "그룹3", "그룹4", "그룹5", "그룹6"
    )


    val stripColors = listOf(Green5, Yellow5, Purple5)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(top = ScaffoldTopContentSpacing)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = ScreenHorizontalPadding)
        ) {
            Text(
                text = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")),
                style = Subtitle_2G,
                color = Typo
            )
            Text(
                modifier = Modifier.padding(top = 6.dp),
                text = stringResource(id = R.string.home_today_schedule),
                style = Body2,
                color = Typo
            )

            userScheduleState.value.let { uiState ->
                when (uiState) {
                    is UserScheduleUiState.Loading -> { }
                    is UserScheduleUiState.Success -> {
                        if (uiState.scheduleOverviewPagination.userScheduleOverview.isEmpty()) {
                            EmptyTodayUserSchedule()
                        } else {
                            TodayUserSchedules(
                                userScheduleOverviewsState = uiState.scheduleOverviewPagination.userScheduleOverview,
                                onLoadMore = {
                                    if (uiState.scheduleOverviewPagination.userScheduleOverview.size == 11) {
                                        homeViewModel.onUserSchedulePageChanged(uiState.scheduleOverviewPagination.page + 1)
                                    }
                                },
                                onLoadPrevious = {
                                    if (uiState.scheduleOverviewPagination.page > 1) {
                                        homeViewModel.onUserSchedulePageChanged(uiState.scheduleOverviewPagination.page - 1)
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = ScreenHorizontalPadding)
        ) {
            HorizontalDivider(
                thickness = 1.dp,
                color = Gray02,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                modifier = Modifier.padding(top = 22.dp),
                text = "닉네임's Group", //TODO : user이름으로 변경
                style = Subtitle_4G,
                color = Typo
            )

            //TODO : 그룹이 있는 경우 vs 없는 경우
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 15.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                var stripColorIndex = 0
                items(items) { item ->
                    val stripColor = stripColors[stripColorIndex]
                    stripColorIndex = (stripColorIndex + 1) % stripColors.size

                    GroupCard(
                        stripColor = stripColor,
                        onClick = { /*TODO : navigate to groupdetail*/ },
                        group = GroupOverviewPaginationState.GroupOverviewState(
                            1,
                            "그룹이름",
                            10,
                            LocalDateTime.now()
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

    }
}

@Composable
fun TodayUserSchedules(
    userScheduleOverviewsState: List<UserScheduleOverviewState>,
    onLoadMore: () -> Unit,
    onLoadPrevious: () -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        itemsIndexed(userScheduleOverviewsState) { index, schedule ->
            TodayUserScheduleCard(
                schedule = schedule,
                onClick = {/*navigation*/}
            )

            if (index == userScheduleOverviewsState.size - 1 && userScheduleOverviewsState.size == 11) {
                onLoadMore()
            }

            if (index == 0) {
                onLoadPrevious()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val navController = rememberNavController()
    HomeScreen(navController = navController)
}