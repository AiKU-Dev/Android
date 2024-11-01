package com.aiku.presentation.ui.component.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.aiku.presentation.theme.CobaltBlue

@Composable
fun FilledChip(
    modifier: Modifier = Modifier,
    containerColor: Color = CobaltBlue,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = modifier.clip(
            RoundedCornerShape(50f)
        ).background(containerColor),
    ) {
        content()
    }
}