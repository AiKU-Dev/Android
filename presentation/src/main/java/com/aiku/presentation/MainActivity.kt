package com.aiku.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.aiku.domain.usecase.LoginUseCase
import com.aiku.domain.usecase.ReadTermsUseCase
import com.aiku.presentation.navigation.MainNavGraph
import com.aiku.presentation.theme.AiKUTheme
import com.aiku.presentation.ui.screen.group.composable.CreateGroupScreen
import com.aiku.presentation.ui.screen.signup.composable.TermsAgreementScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var loginUseCase: LoginUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AiKUTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //MainNavGraph(mainNavController = navController, loginUseCase = loginUseCase)
                    //TermsAgreementScreen(navController)
                    CreateGroupScreen(navController = navController)
                }

            }
        }
    }
}
