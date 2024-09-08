package com.aiku.presentation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.aiku.core.R

enum class DefaultCharacter(
    @DrawableRes val characterRes: Int,
    @StringRes val characterName: Int
) {
    MAN(R.drawable.char_man_1x, R.string.character_man),
    BABY(R.drawable.char_baby_1x, R.string.character_baby),
    SCRATCH(R.drawable.char_scratch_1x, R.string.character_scratch),
    GIRL(R.drawable.char_girl_1x, R.string.character_girl),
}