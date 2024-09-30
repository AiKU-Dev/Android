package com.aiku.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aiku.domain.usecase.LoginUseCase
import com.aiku.presentation.ui.screen.base.BaseScaffold

@Composable
fun MainNavGraph(
    mainNavController: NavHostController,
    loginUseCase: LoginUseCase
) {
    NavHost(navController = mainNavController, startDestination = "btmNav") {
        composable("auth") {
            AuthNavGraph(mainNavController, loginUseCase)
        }
        composable("btmNav") {
            BaseScaffold(mainNavController = mainNavController)
        }
    }
}
