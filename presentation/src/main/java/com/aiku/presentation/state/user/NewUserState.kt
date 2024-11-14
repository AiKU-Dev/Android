package com.aiku.presentation.state.user

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.aiku.domain.model.user.NewUser
import kotlinx.parcelize.Parcelize

@Parcelize
@Immutable
data class NewUserState(
    val nickname: String,
    val profile: ProfileState,
    val email: String,
    val idToken: String,
    val isServicePolicyAgreed: Boolean,
    val isPersonalInformationPolicyAgreed: Boolean,
    val isLocationPolicyAgreed: Boolean,
    val isMarketingPolicyAgreed: Boolean,
    val recommenderNickname: String,
) : Parcelable {

    fun toNewUser(): NewUser = NewUser(
        nickname = nickname,
        profile = profile.toProfile(),
        email = email,
        idToken = idToken,
        isServicePolicyAgreed = isServicePolicyAgreed,
        isPersonalInformationPolicyAgreed = isPersonalInformationPolicyAgreed,
        isLocationPolicyAgreed = isLocationPolicyAgreed,
        isMarketingPolicyAgreed = isMarketingPolicyAgreed,
        recommenderNickname = recommenderNickname
    )

    companion object {
        val EMPTY = NewUserState(
            nickname = "",
            profile = ProfileState.EMPTY,
            email = "",
            idToken = "",
            isServicePolicyAgreed = false,
            isPersonalInformationPolicyAgreed = false,
            isLocationPolicyAgreed = false,
            isMarketingPolicyAgreed = false,
            recommenderNickname = ""
        )
    }
}
