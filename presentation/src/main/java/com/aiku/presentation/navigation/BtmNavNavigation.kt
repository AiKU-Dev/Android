package com.aiku.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aiku.presentation.ui.screen.home.HomeScreen
import com.aiku.presentation.ui.screen.my.MyScreen
import com.aiku.presentation.ui.screen.schedule.MyScheduleScreen

@Composable
fun BtmNavGraph(navController: NavHostController) {

        NavHost(
            navController = navController,
            startDestination = "home"
        ) {
            composable("home") { HomeScreen(navController) }
            composable("mySchedule") { MyScheduleScreen(navController) }
            composable("my") { MyScreen(navController) }
        }

}