package com.aiku.presentation.ui.component.chip

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aiku.core.R
import com.aiku.domain.model.group.type.ProfileBackground
import com.aiku.domain.model.group.type.ProfileCharacter
import com.aiku.presentation.ui.component.background.CircularBackground
import com.aiku.presentation.util.getColor
import com.aiku.presentation.util.getDrawableResId

@Composable
fun DefaultProfileIcon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    showClickRipple: Boolean = true,
    character: ProfileCharacter,
    background: ProfileBackground
) {
    CircularBackground(
        modifier = modifier,
        onClick = onClick,
        showClickRipple = showClickRipple,
        color = background.getColor(),
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = character.getDrawableResId()),
            contentDescription = stringResource(id = R.string.profile_image_setup_content_description)
        )
    }
}

@Preview
@Composable
private fun DefaultProfileIconPreview() {
    DefaultProfileIcon(
        character = ProfileCharacter.C01,
        background = ProfileBackground.BLUE
    )
}