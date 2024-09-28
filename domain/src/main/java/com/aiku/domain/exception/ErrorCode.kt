package com.aiku.domain.exception

import java.io.FileNotFoundException

/** Server-side Error code */
const val UNKNOWN = 0
const val ALREADY_EXIST_PHONE_NUMBER = 10001
const val ALREADY_EXIST_NICKNAME = 10002

/** 로그인 */
const val INVALID_ID_TOKEN = 11001
const val USER_NOT_FOUND = 11002

const val INVALID_TOKEN = 11004
const val EXPIRED_TOKEN = 11005

/** Client-side Error code */
const val REQUIRE_NICKNAME_INPUT = 20000
const val REQUIRE_PHONE_NUMBER_INPUT = 20001
const val INVALID_NICKNAME_FORMAT = 20002
const val INVALID_PHONE_NUMBER_FORMAT = 20003
const val NICKNAME_LENGTH_EXCEED = 20004


/** HTTP Status Codes */
//object HttpStatusCodes {
//    const val BAD_REQUEST = 400
//    const val UNAUTHORIZED = 401
//    const val FORBIDDEN = 403
//    const val NOT_FOUND = 404
//    const val REQUEST_TIMEOUT = 408
//
//    const val INTERNAL_SERVER_ERROR = 500
//    const val NOT_IMPLEMENTED = 501
//    const val BAD_GATEWAY = 502
//    const val SERVICE_UNAVAILABLE = 503
//    const val GATEWAY_TIMEOUT = 504
//}