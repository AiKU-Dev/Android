package com.aiku.presentation.ui.signup.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aiku.presentation.ui.signup.viewmodel.SaveProfileUiState
import com.aiku.presentation.ui.signup.viewmodel.MyPageViewModel

@Composable
fun ProfileEditScreen(
    viewModel: MyPageViewModel = hiltViewModel()
) {

    val saveProfileUiState by viewModel.saveProfileUiState.collectAsStateWithLifecycle()

    when (saveProfileUiState) {
        is SaveProfileUiState.Loading -> {
            // 대충 로딩뷰
        }
        is SaveProfileUiState.Success -> {
            // 대충 프로필 변경 성공
        }
        is SaveProfileUiState.AlreadyExistPhoneNumber -> {
            // 대충 이미 존재하는 전화번호일 때 UI
            // 텍스트 필드 강조 처리라던가
            // 스낵바 띄우기라던가 ...
        }
        is SaveProfileUiState.AlreadyExistNickname -> {
            // 대충 이미 존재하는 닉네임일 때 UI
        }
        is SaveProfileUiState.InvalidNicknameFormat -> {
            // 대충 잘못된 닉네임 형식일 때 UI
        }
        is SaveProfileUiState.InvalidPhoneNumberFormat -> {
            // 대충 잘못된 전화번호 형식일 때 UI
        }
        is SaveProfileUiState.Idle -> {
            // 아무것도 아닌 상태
        }
    }
}