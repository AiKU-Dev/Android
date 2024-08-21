package com.aiku.core.base

/**
 * @param Initial 초기 상태
 * @param Success 성공
 * @param Error 에러. 여러 작업 중 하나만 실패하더라도 Error 상태임
 * @param Loading 로딩. 동시에 진행 중인 모든 작업이 완료되어야 Loading 상태가 해제됨
 */
sealed class UiState {
    data object Initial: UiState()
    data object Success: UiState()
    data class Error(val error: Throwable?): UiState()
    data object Loading: UiState()
}