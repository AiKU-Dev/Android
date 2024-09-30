package com.aiku.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aiku.presentation.navigation.route.BtmNavRoute
import com.aiku.presentation.ui.screen.home.HomeScreen
import com.aiku.presentation.ui.screen.my.MyPageScreen
import com.aiku.presentation.ui.screen.schedule.MyScheduleScreen

@Composable
fun BottomNavGraph(
    btmNavController: NavHostController
) {
    NavHost(navController = btmNavController, startDestination = BtmNavRoute.HOME.name) {
        composable(BtmNavRoute.HOME.name) {
            HomeScreen(btmNavController)
        }
        composable(BtmNavRoute.MYSCHEDULE.name) {
            MyScheduleScreen(btmNavController)
        }
        composable(BtmNavRoute.MYPAGE.name) {
            MyPageScreen(btmNavController)
        }
    }
}