package com.aiku.presentation.ui.component.chip

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import com.aiku.domain.model.group.type.ProfileBackground
import com.aiku.domain.model.group.type.ProfileCharacter
import com.aiku.domain.model.group.type.ProfileType
import com.aiku.presentation.state.group.MemberState
import com.aiku.presentation.state.group.ProfileState
import com.aiku.presentation.ui.component.background.CircularBackground
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProfileIcon(
    modifier: Modifier = Modifier,
    profile: ProfileState
) {
    CircularBackground(
        modifier = modifier,
    ) {
        when (profile.type) {
            ProfileType.IMG -> {
                GlideImage(
                    model = profile.image,
                    contentScale = ContentScale.Crop,
                    contentDescription = stringResource(id = com.aiku.core.R.string.profile_image)
                )
            }

            ProfileType.CHAR -> {
                DefaultProfileIcon(
                    onClick = { /*TODO*/ },
                    character = profile.character,
                    background = profile.background
                )
            }
        }
    }
}