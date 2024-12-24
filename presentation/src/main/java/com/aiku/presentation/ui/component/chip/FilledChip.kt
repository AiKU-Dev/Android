package com.aiku.presentation.ui.component.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aiku.core.theme.Body1
import com.aiku.presentation.R
import com.aiku.presentation.theme.CobaltBlue

@Composable
fun FilledChip(
    modifier: Modifier = Modifier,
    containerColor: Color = CobaltBlue,
    contentPadding: PaddingValues = PaddingValues(horizontal = 10.dp, vertical = 6.dp),
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = modifier.clip(
            RoundedCornerShape(50f)
        ).background(containerColor)
            .padding(contentPadding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun FilledChipPreview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        // 기본 FilledChip
        FilledChip {
            Text(
                text = "Default Chip",
                color = Color.White,
                style = Body1
            )
        }

        // 사용자 정의 색상 및 패딩 적용
        FilledChip(
            containerColor = Color.Green,
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                text = "Custom Chip",
                color = Color.White,
                style = Body1
            )
        }

        // 아이콘 추가 예시
        FilledChip(
            containerColor = Color.Cyan,
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
        ) {
            Icon(
                painter = painterResource(id = com.aiku.core.R.drawable.ic_like),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Chip with Icon",
                color = Color.White,
                style = Body1
            )
        }
    }
}
