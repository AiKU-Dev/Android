package com.aiku.presentation.ui.screen.auth.composable

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aiku.core.base.UiState
import com.aiku.domain.delegate.UserDelegate
import com.aiku.presentation.theme.AiKUTheme
import com.aiku.presentation.theme.BrandColor
import com.aiku.presentation.theme.FullWidthButtonTextSize
import com.aiku.presentation.theme.KakaoBlack
import com.aiku.presentation.theme.KakaoYellow
import com.aiku.presentation.theme.ScreenHorizontalPadding
import com.aiku.presentation.ui.component.FullWidthButton
import com.aiku.presentation.ui.screen.auth.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val uiState by loginViewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = scrollState)
            .background(BrandColor)
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

//            Image(
//                modifier = Modifier.padding(start = 27.dp),
//                painter = painterResource(id = R.drawable.ic_kakao_message_circle),
//                contentDescription = "카카오톡 아이콘"
//            )
        }
    }

    // UI 상태에 따라 처리
    when (uiState) {
        is UiState.Loading -> {
        }
        is UiState.Success -> {
            //TODO : 홈 화면으로 전환
        }
        is UiState.Error -> {
            val errorMessage = (uiState as UiState.Error).error?.message
            Log.e("LoginScreen", "Error occurred: $errorMessage")
            //TODO : 회원가입 화면으로 전환
        }
        else -> {
            // 초기 상태 또는 다른 상태 처리
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
