package com.aiku.core.qualifer

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ResponseExceptionInterceptor

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ResponseParsingInterceptor

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class AuthHeaderInterceptor