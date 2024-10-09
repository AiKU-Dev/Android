package com.aiku.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.aiku.presentation.navigation.route.Routes
import com.aiku.presentation.state.group.GroupState
import com.aiku.presentation.state.schedule.GroupScheduleOverviewState
import com.aiku.presentation.ui.group.GroupScreen
import com.aiku.presentation.ui.group.schedule.WaitingScheduleScreen
import com.aiku.presentation.ui.screen.signup.composable.ProfileEditScreen

@Composable
fun AikuNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH,
        modifier = modifier
    ) {
        navigation(
            startDestination = Routes.Auth.LOGIN,
            route = Routes.Auth.GRAPH
        ) {
            composable(Routes.Auth.LOGIN) {
                // 로그인 화면
            }
            composable(Routes.Auth.TERM_AGREEMENT) {
                // 약관 동의 화면
            }
            composable(Routes.Auth.TERM_CONTENT) {
                // 약관 내용 화면
            }
            composable(Routes.Auth.PROFILE_EDIT) {
                ProfileEditScreen(navController = navController)
            }
        }

        navigation(
            startDestination = Routes.Main.BtmNav.HOME,
            route = Routes.Main.BtmNav.GRAPH
        ) {
            composable(Routes.Main.BtmNav.HOME) {
                // 홈 화면
            }
            composable(Routes.Main.BtmNav.MY_SCHEDULE) {
                // 내 약속 화면
            }
            composable(Routes.Main.BtmNav.MY_PAGE) {
                // 마이페이지 화면
            }
            composable(Routes.Main.TopNav.SHOP) {
                // 상점 화면
            }
            composable(Routes.Main.TopNav.NOTIFICATION) {
                // 알림 화면
            }
        }

        navigation(
            startDestination = Routes.Group.GROUP,
            route = Routes.Group.GRAPH
        ) {
            composable(Routes.Group.GROUP) {
                GroupScreen(navController = navController, groupId = 1, groupName = "놀자놀자팟")
            }
            composable(Routes.Group.SCHEDULE_WAITING) {
                val group = it.arguments?.getParcelable<GroupState>(GROUP, GroupState::class.java)
                val groupScheduleOverview = it.arguments?.getParcelable<GroupScheduleOverviewState>(GROUP_SCHEDULE_OVERVIEW, GroupScheduleOverviewState::class.java)

                WaitingScheduleScreen(
                    navController = navController,
                    group = group!!,
                    schedule = groupScheduleOverview!!  // TODO : Nullable 처리
                )
            }
            composable(Routes.Group.SCHEDULE_RUNNING) {
                // 진행 중 약속 화면
            }
            navigation(
                startDestination = Routes.Group.CreateSchedule.GRAPH,
                route = Routes.Group.CreateSchedule.GRAPH
            ) {
                // 약속 생성 화면 그래프
            }
        }
    }

}