package com.aiku.domain.model

data class UserModel(
    val id : String, //회원번호
    val email: String, //이메일
    val nickname: String, //닉네임
    val profileImg: String, //프로필 사진
    val phoneNumber: String //전화번호
)