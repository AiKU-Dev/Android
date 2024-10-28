package com.aiku.presentation.ui.component.textfield

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.foundation.text2.input.CodepointTransformation
import androidx.compose.foundation.text2.input.InputTransformation
import androidx.compose.foundation.text2.input.TextFieldLineLimits
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.aiku.core.theme.Caption1
import com.aiku.core.theme.Subtitle3
import com.aiku.presentation.theme.ColorError
import com.aiku.presentation.theme.Gray02
import com.aiku.presentation.theme.Gray03

/**
 * @param label 하단 라벨
 * @param showLabel 라벨 표시 여부. true일 경우 label이 비어있더라도 공간을 차지함.
 * @param placeholder placeholder
 * @param lineColor 하단 라인 색상
 * @param showLengthIndicator 글자 수 표시 여부
 * @param maxLength 최대 글자 수
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BottomLinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "",
    showLabel: Boolean = true,
    placeholder: String = "",
    lineColor: Color = Gray02,
    showLengthIndicator: Boolean = false,
    maxLength: Int = 0,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    inputTransformation: InputTransformation? = null,
    textStyle: TextStyle = Subtitle3.copy(fontWeight = FontWeight.Medium),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    lineLimits: TextFieldLineLimits = TextFieldLineLimits.Default,
    onTextLayout: (Density.(getResult: () -> TextLayoutResult?) -> Unit)? = null,
    interactionSource: MutableInteractionSource? = null,
    cursorBrush: Brush = SolidColor(Color.Black),
    codepointTransformation: CodepointTransformation? = null,
    scrollState: ScrollState = rememberScrollState()
) {

    BasicTextField2(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        readOnly = readOnly,
        inputTransformation = inputTransformation,
        textStyle = textStyle,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        lineLimits = lineLimits,
        onTextLayout = onTextLayout,
        interactionSource = interactionSource,
        cursorBrush = cursorBrush,
        codepointTransformation = codepointTransformation,
        scrollState = scrollState,
        decorator = @Composable { innerTextField ->
            Column(
                modifier = Modifier,
            ) {
                Row {
                    Box() {
                        if (value.isEmpty())
                            Text(text = placeholder, color = Gray03, style = textStyle)
                        innerTextField()
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    if (showLengthIndicator) {
                        Text(
                            color = if (value.length > maxLength) ColorError else Gray03,
                            text = "${value.length}/${maxLength}",
                            style = Caption1
                        )
                    }
                }
                HorizontalDivider(
                    modifier = Modifier.padding(top = 4.dp),
                    color = lineColor,
                    thickness = 1.dp
                )
                if (showLabel)
                    Text(text = label.ifEmpty { " " }, style = Caption1, modifier = Modifier.padding(top = 4.dp))
            }
        }
    )
}


@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true, name = "Bottom Lined TextField")
@Composable
fun BottomLinedTextFieldPreview() {
    BottomLinedTextField(
        value = "닉네임12",
        onValueChange = {},
        label = "최대 6글자 입력 가능합니다.",
        showLabel = true,
        placeholder = "placeholder",
        lineColor = Gray02,
        showLengthIndicator = true,
        maxLength = 6,
        enabled = true,
        readOnly = false,
        inputTransformation = null,
        textStyle = Subtitle3,
        keyboardOptions = KeyboardOptions.Default,
        keyboardActions = KeyboardActions.Default,
        lineLimits = TextFieldLineLimits.Default,
        onTextLayout = null,
        interactionSource = null,
        cursorBrush = SolidColor(Color.Black),
        codepointTransformation = null,
        scrollState = rememberScrollState()
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true, name = "Bottom Lined TextField - No Text")
@Composable
fun BottomLinedTextFieldNoTextPreview() {
    BottomLinedTextField(
        value = "",
        onValueChange = {},
        label = "최대 6글자 입력 가능합니다.",
        showLabel = true,
        placeholder = "닉네임을 입력하세요",
        lineColor = Gray02,
        showLengthIndicator = true,
        maxLength = 6,
        enabled = true,
        readOnly = false,
        inputTransformation = null,
        textStyle = Subtitle3,
        keyboardOptions = KeyboardOptions.Default,
        keyboardActions = KeyboardActions.Default,
        lineLimits = TextFieldLineLimits.Default,
        onTextLayout = null,
        interactionSource = null,
        cursorBrush = SolidColor(Color.Black),
        codepointTransformation = null,
        scrollState = rememberScrollState()
    )
}
