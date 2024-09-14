package com.aiku.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.aiku.domain.usecase.LoginUseCase
import com.aiku.presentation.navigation.route.SignUpRoute
import com.aiku.presentation.ui.screen.login.composable.LoginScreen
import com.aiku.presentation.ui.screen.signup.composable.ProfileEditScreen

@Composable
fun AuthNavGraph(
    mainNavController : NavHostController,
    loginUseCase: LoginUseCase
) {
    val authNavController = rememberNavController()

    NavHost(navController = authNavController, startDestination = SignUpRoute.LOGIN.name) {
        composable(SignUpRoute.LOGIN.name) {
            LoginScreen(authNavController, loginUseCase)
        }
        composable(SignUpRoute.TERM.name) {
            // TODO : 약관 페이지
        }
        composable(SignUpRoute.PROFILE_EDIT.name) {
            ProfileEditScreen(
                modifier = Modifier,
                navController = authNavController
            )
        }
    }
}

