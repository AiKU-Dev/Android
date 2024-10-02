package com.aiku.presentation.ui.screen.login.composable

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.aiku.domain.usecase.LoginUseCase
import com.aiku.presentation.navigation.route.BtmNavRoute
import com.aiku.presentation.navigation.route.SignUpRoute
import com.aiku.presentation.theme.AiKUTheme
import com.aiku.presentation.theme.FullWidthButtonTextSize
import com.aiku.presentation.theme.KakaoBlack
import com.aiku.presentation.theme.KakaoYellow
import com.aiku.presentation.theme.PrimaryColor
import com.aiku.presentation.theme.ScreenHorizontalPadding
import com.aiku.presentation.ui.component.button.FullWidthButton
import com.aiku.presentation.ui.screen.login.viewmodel.LoginUiState
import com.aiku.presentation.ui.screen.login.viewmodel.LoginViewModel
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    navController: NavController,
    loginUseCase: LoginUseCase,
    loginViewModel: LoginViewModel = hiltViewModel(),
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

                LoginUiState.Success -> { navController.navigate(SignUpRoute.TERM_AGREEMENT.name) }

                LoginUiState.OCIDFetchFailed, LoginUiState.ServerError -> {}
            }
        }
    }


}
