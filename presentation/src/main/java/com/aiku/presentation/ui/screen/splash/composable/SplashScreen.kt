package com.aiku.presentation.ui.screen.splash.composable

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aiku.domain.usecase.LoginUseCase
import com.aiku.presentation.ui.screen.login.viewmodel.AutoLoginUiState
import com.aiku.presentation.ui.screen.login.viewmodel.LoginViewModel

@Composable
fun SplashScreen(
    loginUseCase: LoginUseCase,
    loginViewModel: LoginViewModel = hiltViewModel(),
) {
    val autoLoginUiState by loginViewModel.autoLoginUiState.collectAsStateWithLifecycle()
    val animationCompleted = remember { mutableStateOf(false) }

    // 자동 로그인 시도
    LaunchedEffect(Unit) {
        loginViewModel.autoLogin(loginUseCase)
    }

    // animation 완료 후, 화면 전환
    LaunchedEffect(animationCompleted.value) {
        if (animationCompleted.value) {
            when (autoLoginUiState) {
                AutoLoginUiState.Idle -> Unit
                AutoLoginUiState.Loading -> Unit
                AutoLoginUiState.Success -> { Log.d("Auto Login", "Auto Login Success")} // 홈 화면으로 이동
                AutoLoginUiState.TokenNotFound -> { Log.d("Auto Login", "Auto Login Failure") } // 로그인 화면으로 이동
            }
        }
    }

    // animation
    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(3000)
        animationCompleted.value = true
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
    }
}
