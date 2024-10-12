package com.aiku.aiku

import android.app.Application
import android.util.Log
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.util.Utility
import com.kakao.vectormap.KakaoMapSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AikuApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Kakao Login SDK 초기화
        KakaoSdk.init(this, BuildConfig.NATIVE_APP_KEY)
        // Kakao Map SDK 초기화
        KakaoMapSdk.init(this, BuildConfig.NATIVE_APP_KEY)

        var keyHash = Utility.getKeyHash(this)
        Log.i("AikuApplication", "$keyHash")
    }
}