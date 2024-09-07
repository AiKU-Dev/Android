package com.aiku.presentation.ui.screen.login.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aiku.presentation.theme.AiKUTheme
import com.aiku.presentation.theme.FullWidthButtonTextSize
import com.aiku.presentation.theme.KakaoBlack
import com.aiku.presentation.theme.KakaoYellow
import com.aiku.presentation.theme.PrimaryColor
import com.aiku.presentation.theme.ScreenHorizontalPadding
import com.aiku.presentation.ui.component.FullWidthButton
import com.aiku.presentation.ui.screen.login.viewmodel.LoginUiState
import com.aiku.presentation.ui.screen.login.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel()
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
                background = KakaoYellow,
                content = {
                    Text(
                        text = "카카오 로그인",
                        color = KakaoBlack,
                        fontSize = FullWidthButtonTextSize
                    )
                },
                onClick = {
                    loginViewModel.login(useKakaoTalk = true)
                }
            )

        }
    }

    val loginUiState by loginViewModel.loginUiState.collectAsStateWithLifecycle()

    when (loginUiState) {
        is LoginUiState.Idle -> {
        }
        is LoginUiState.Loading -> {
            CircularProgressIndicator()
        }
        is LoginUiState.Success -> {
            Text("Login Successful!")
        }
        is LoginUiState.InvalidIdToken -> {
            Text("Invalid ID Token!")
        }
        is LoginUiState.UserNotFound -> {
            Text("User Not Found!")
        }
    }


}

@Preview
@Composable
private fun LoginPreview() {
    AiKUTheme {
        LoginScreen()
    }
}
