package com.aiku.presentation.ui.component.checkbox

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aiku.core.R
import com.aiku.core.theme.Caption1_Medium
import com.aiku.core.theme.Subtitle3_SemiBold
import com.aiku.presentation.theme.Gray02
import com.aiku.presentation.theme.Green5
import com.aiku.presentation.theme.Typo

/**
 * 텍스트와 함께 표시되는 커스터마이징 가능한 체크박스 컴포넌트
 *
 * @param modifier 레이아웃에 적용할 Modifier
 * @param showCheckMark 체크박스에 체크마크를 표시할지 여부
 *                      null일 경우 checkedState에 따라 자동 설정
 * @param checkedState 현재 체크박스의 상태 : true면 체크된 상태, false면 체크되지 않은 상태
 * @param onCheckedChange 체크박스 상태가 변경될 때 호출되는 콜백 함수
 * @param backgroundColor 체크박스의 배경 색상 : null로 전달하면 배경을 표시하지 않음
 * @param borderColor 체크박스의 테두리 색상 : null로 전달하면 테두리를 표시하지 않음
 * @param shape 체크박스의 모양 : 기본값은 RoundedCornerShape(5.dp)
 * @param text 체크박스 옆에 표시될 텍스트
 * @param textStyle 텍스트에 적용할 스타일 : 기본값은 Caption1_Medium
 */


private val CheckBoxSize = 22.dp
private val CheckMarkSize = 22.dp
private val CheckBoxContentPadding = 10.dp

@Composable
fun CheckBoxWithText(
    modifier: Modifier,
    showCheckMark: Boolean? = null, //null : checkedState에 따라 자동 설정
    checkedState: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    backgroundColor: Color?, // null : 배경 없음
    borderColor: Color? = backgroundColor, //null : 윤곽선 없음
    shape: Shape = RoundedCornerShape(5.dp),
    text: String,
    textStyle: TextStyle = Caption1_Medium,
) {

    val shouldShowCheckMark = showCheckMark ?: checkedState

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.clickable { onCheckedChange(!checkedState) }
    ) {
        Box(
            modifier = Modifier
                .size(CheckBoxSize)
                .let {
                    if (backgroundColor != null) {
                        it.background(color = backgroundColor, shape = shape)
                    } else it
                }
                .let {
                    if (borderColor != null) {
                        it.border(1.dp, borderColor, shape)
                    } else it
                }
        ) {
            if (shouldShowCheckMark) {
                Image(
                    modifier = Modifier.size(CheckMarkSize),
                    painter = painterResource(id = R.drawable.ic_check),
                    contentDescription = "check mark"
                )
            }
        }

        Text(
            modifier = Modifier.padding(start = CheckBoxContentPadding),
            text = text,
            style = textStyle,
            color = Typo
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CheckBoxWithTextDefaultPreview() {
    val checkedState = remember { mutableStateOf(false) }

    CheckBoxWithText(
        modifier = Modifier.padding(16.dp),
        checkedState = checkedState.value,
        onCheckedChange = { newValue -> checkedState.value = newValue },
        text = "기본 체크박스",
        backgroundColor = Gray02,
        borderColor = Gray02,
        shape = RoundedCornerShape(5.dp),
        textStyle = TextStyle.Default
    )
}

@Preview(showBackground = true)
@Composable
fun CheckBoxWithTextHideCheckMarkPreview() {
    val checkedState = remember { mutableStateOf(false) }

    CheckBoxWithText(
        modifier = Modifier.padding(16.dp),
        showCheckMark = false,
        checkedState = checkedState.value,
        onCheckedChange = { newValue -> checkedState.value = newValue },
        text = "체크마크 숨김",
        backgroundColor = null,
        borderColor = Gray02,
        shape = RoundedCornerShape(5.dp),
        textStyle = TextStyle.Default
    )
}

@Preview(showBackground = true)
@Composable
fun CheckBoxWithTextShowCheckMarkPreview() {
    val checkedState = remember { mutableStateOf(true) }

    CheckBoxWithText(
        modifier = Modifier.padding(16.dp),
        showCheckMark = true,
        checkedState = checkedState.value,
        onCheckedChange = { newValue -> checkedState.value = newValue },
        text = "체크마크 보임",
        backgroundColor = Gray02,
        borderColor = Gray02,
        shape = RoundedCornerShape(5.dp),
        textStyle = TextStyle.Default
    )
}

@Preview(showBackground = true)
@Composable
fun CheckBoxWithTextDynamicCheckMarkPreview() {
    val checkedState = remember { mutableStateOf(true) }

    CheckBoxWithText(
        modifier = Modifier.padding(16.dp),
        showCheckMark = null, // checkedState에 따라 동작
        checkedState = checkedState.value,
        onCheckedChange = { newValue -> checkedState.value = newValue },
        text = "체크 상태에 따라 체크마크 표시",
        backgroundColor = if (checkedState.value) Green5 else Gray02,
        shape = RoundedCornerShape(5.dp),
        textStyle = TextStyle.Default
    )
}