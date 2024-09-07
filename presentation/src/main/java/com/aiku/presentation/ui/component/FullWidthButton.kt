package com.aiku.presentation.ui.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.aiku.presentation.theme.FullWidthButtonCornerRadius
import com.aiku.presentation.theme.FullWidthButtonVerticalPadding


//화면 너비의 직사각형 버튼
@Composable
fun FullWidthButton(
    enabled : Boolean = true,
    background: Color,
    content: @Composable RowScope.() -> Unit,
    onClick: () -> Unit = {}
) {
    Button(
        enabled = enabled,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = background,
            disabledContainerColor = background),
        shape = RoundedCornerShape(FullWidthButtonCornerRadius),
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues( // 미설정 시, material3 Button 기본 패딩 O
            start = 0.dp,
            end = 0.dp,
            top = FullWidthButtonVerticalPadding,
            bottom = FullWidthButtonVerticalPadding
        ),
        content = { content() }
    )
}