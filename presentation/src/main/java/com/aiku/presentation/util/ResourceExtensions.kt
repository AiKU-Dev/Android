package com.aiku.presentation.util

import androidx.compose.ui.graphics.Color
import com.aiku.domain.model.group.type.ProfileCharacter
import com.aiku.core.R
import com.aiku.domain.model.group.type.ProfileBackground
import com.aiku.presentation.theme.Green5
import com.aiku.presentation.theme.Purple5
import com.aiku.presentation.theme.Yellow5

fun ProfileCharacter.getDrawableResId(): Int {
    return when (this) {
        ProfileCharacter.C01 -> R.drawable.char_man_1x
        ProfileCharacter.C02 -> R.drawable.char_baby_1x
        ProfileCharacter.C03 -> R.drawable.char_scratch_1x
        ProfileCharacter.C04 -> R.drawable.char_girl_1x
        ProfileCharacter.NONE -> R.drawable.char_man_1x
    }
}

fun ProfileBackground.getColor(): Color {
    return when (this) {
        ProfileBackground.RED -> Yellow5
        ProfileBackground.GREEN -> Green5
        ProfileBackground.BLUE -> Purple5
        ProfileBackground.NONE -> Green5
    }
}