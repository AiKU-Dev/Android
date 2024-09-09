package com.aiku.presentation.ui.component.navigation

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.aiku.core.theme.Caption1
import com.aiku.presentation.navigation.route.BtmNav
import com.aiku.presentation.theme.Gray02
import com.aiku.presentation.theme.Gray03
import com.aiku.presentation.theme.Green5
import com.aiku.presentation.theme.Typo

@Composable
fun BottomNavigation(navController: NavHostController) {

    val items = listOf(
        BtmNav.MySchedule,
        BtmNav.Home,
        BtmNav.My
    )

    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .border(BorderStroke(1.dp, Gray02), shape = RectangleShape)
            .padding(top = 1.dp),
        containerColor = Color.White
    ) {
        items.forEach {
            Log.d("btmnav", "바텀네비생성")
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = it.icon),
                        contentDescription = it.label
                    )
                },
                label = { Text(it.label, style = Caption1) },
                selected = navController.currentDestination?.route == it.route,
                onClick = {
                    navController.navigate(it.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Green5,
                    unselectedIconColor = Gray03,
                    selectedTextColor = Typo,
                    unselectedTextColor = Gray03,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationPreview() {
    val navController = rememberNavController()
    BottomNavigation(
        navController = navController
    )
}