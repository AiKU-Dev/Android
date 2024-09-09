package com.aiku.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aiku.domain.usecase.LoginUseCase
import com.aiku.presentation.navigation.BtmNavGraph
import com.aiku.presentation.navigation.SignUpNavGraph
import com.aiku.presentation.theme.AiKUTheme
import com.aiku.presentation.ui.screen.home.BaseScaffold
import com.aiku.presentation.ui.screen.home.HomeScreen
import com.aiku.presentation.ui.screen.login.composable.LoginScreen
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
                    //SignUpNavGraph(navController = navController, loginUseCase)
                    BaseScaffold(navController = navController)
                }

            }
        }
    }
}
