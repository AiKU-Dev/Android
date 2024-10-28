package com.aiku.presentation.ui.screen.login.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.aiku.domain.usecase.LoginUseCase
import com.aiku.presentation.theme.FullWidthButtonTextSize
import com.aiku.presentation.theme.KakaoBlack
import com.aiku.presentation.theme.KakaoYellow
import com.aiku.presentation.theme.PrimaryColor
import com.aiku.presentation.theme.ScreenHorizontalPadding
import com.aiku.presentation.ui.component.button.FullWidthButton
import com.aiku.presentation.ui.screen.login.viewmodel.LoginUiState
import com.aiku.presentation.ui.screen.login.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    loginUseCase: LoginUseCase,
    loginViewModel: LoginViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit,
) {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = scrollState)
            .background(PrimaryColor)
            .padding(horizontal = ScreenHorizontalPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            contentAlignment = Alignment.CenterStart
        ) {
            FullWidthButton(
                background = ButtonDefaults.buttonColors(KakaoYellow),
                content = {
                    Text(
                        text = "카카오 로그인",
                        color = KakaoBlack,
                        fontSize = FullWidthButtonTextSize
                    )
                },
                onClick = {
                    loginViewModel.login(useKakaoTalk = true, loginUseCase)
                }
            )

        }
    }

    LaunchedEffect(Unit) {
        loginViewModel.loginUiState.collect { uiState ->
            when (uiState) {
                LoginUiState.Idle -> {}
                LoginUiState.Loading -> {}
                LoginUiState.Success -> onLoginSuccess()
                LoginUiState.OCIDFetchFailed, LoginUiState.ServerError -> {}
            }
        }
    }
}
