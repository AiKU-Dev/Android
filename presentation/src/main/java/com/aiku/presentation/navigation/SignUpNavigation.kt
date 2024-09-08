package com.aiku.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aiku.presentation.ui.screen.login.composable.LoginScreen

enum class Routes {
    Splash,
    Login
}

@Composable
fun SignUpNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.Splash.name) {

//        composable(Routes.Login.name) {
//            LoginScreen(navController)
//        }
    }
}
