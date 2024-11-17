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

        /** KAKAO SDK (로그인) 초기화 */
        KakaoSdk.init(this, BuildConfig.NATIVE_APP_KEY)
        /** KAKAO MAP 초기화 */
        KakaoMapSdk.init(this, BuildConfig.NATIVE_APP_KEY)

        /** KAKAO 디버그 키 해시값 확인 */
        var keyHash = Utility.getKeyHash(this)
        Log.i("AikuApplication", "$keyHash")
    }
}