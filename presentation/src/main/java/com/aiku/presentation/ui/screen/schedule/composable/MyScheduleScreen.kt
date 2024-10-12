package com.aiku.presentation.ui.screen.schedule.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun MyScheduleScreen(navController: NavHostController) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "내 약속 화면")
        }

}

@Preview(showBackground = true)
@Composable
fun MyScheduleScreenPreview() {
    val navController = rememberNavController()
    MyScheduleScreen(navController = navController)
}