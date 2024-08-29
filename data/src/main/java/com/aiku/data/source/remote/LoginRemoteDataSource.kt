package com.aiku.data.source.remote

import android.content.Context
import com.aiku.data.model.remote.LoginResponse

interface LoginRemoteDataSource {
    suspend fun loginWithKakaoTalk(): LoginResponse //카카오톡으로 로그인
    suspend fun loginWithKakaoAccount(): LoginResponse //카카오 계정으로 로그인
}
