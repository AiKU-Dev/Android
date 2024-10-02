package com.aiku.presentation.ui.component.chip

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.aiku.core.R
import com.aiku.domain.model.group.type.ProfileType
import com.aiku.presentation.state.user.ProfileState
import com.aiku.presentation.ui.component.background.CircularBackground

@Composable
fun ProfileIcon(
    modifier: Modifier = Modifier,
    showClickRipple: Boolean = true,
    onClick: () -> Unit = {},
    profile: ProfileState
) {
    CircularBackground(
        modifier = modifier,
        showClickRipple = showClickRipple,
        onClick = onClick,
    ) {
        when (profile.type) {
            ProfileType.IMG -> {
                AsyncImage(
                    model = profile.image,
                    contentDescription = stringResource(id = R.string.profile_image),
                    placeholder = null,
                    contentScale = ContentScale.Crop
                )
            }

            ProfileType.CHAR -> {
                DefaultProfileIcon(
                    showClickRipple = showClickRipple,
                    character = profile.character,
                    background = profile.background
                )
            }
        }
    }
}