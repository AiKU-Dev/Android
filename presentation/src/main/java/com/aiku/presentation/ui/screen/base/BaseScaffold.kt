package com.aiku.presentation.ui.screen.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.aiku.presentation.navigation.BottomNavGraph
import com.aiku.presentation.ui.component.navigation.BottomNavigation

@Composable
fun BaseScaffold(
    mainNavController : NavHostController
) {
    val btmNavController = rememberNavController()

    val onTabSelected = { tab: String ->
        btmNavController.navigate(tab) {
            popUpTo(btmNavController.graph.findStartDestination().id) { saveState = true }
            launchSingleTop = true
            restoreState = true
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                BottomNavigation(btmNavController, onTabSelected)
            }
        },

    ) { innerPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)) {
            BottomNavGraph(btmNavController = btmNavController)
        }

    }
}