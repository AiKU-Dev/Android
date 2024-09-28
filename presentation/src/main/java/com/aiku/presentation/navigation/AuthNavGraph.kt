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
import com.aiku.presentation.ui.screen.signup.composable.TermsAgreementScreen
import com.aiku.presentation.ui.screen.signup.composable.TermsContentScreen

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
        composable(SignUpRoute.TERM_AGREEMENT.name) {
            TermsAgreementScreen(authNavController)
        }
        composable("${SignUpRoute.TERM_CONTENT.name}/{identifier}") { backStackEntry ->
            val identifier = backStackEntry.arguments?.getString("identifier")?.toIntOrNull()
            TermsContentScreen(identifier = identifier)
        }
        composable(SignUpRoute.PROFILE_EDIT.name) {
            ProfileEditScreen(
                modifier = Modifier,
                navController = authNavController
            )
        }
    }
}


