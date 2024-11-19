package com.aiku.core.qualifer

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class AuthHeaderInterceptor

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class KakaoAuthHeaderInterceptor

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ResponseInterceptor

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class KakaoResponseInterceptor

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class BaseUrl
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class KakaoBaseUrl

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Auth
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class NoAuth
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class KakaoAuth