package com.aiku.presentation.ui.component.checkbox

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aiku.core.R
import com.aiku.core.theme.Subtitle3
import com.aiku.presentation.theme.Gray02
import com.aiku.presentation.theme.Green5
import com.aiku.presentation.theme.Typo

@Composable
fun CheckMarkWithText(
    modifier: Modifier,
    checkedState: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    content: String,
    onNavigateToTermContent: () -> Unit
) {

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.clickable(onClick = { onCheckedChange(!checkedState) })
    ) {

        Image(
            modifier = Modifier.size(CheckMarkSize),
            painter = painterResource(id = R.drawable.ic_check),
            contentDescription = "check mark",
            colorFilter = ColorFilter.tint(color = if (checkedState) Green5 else Gray02)
        )

        Text(
            text = content,
            style = Subtitle3,
            color = Typo,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier
                .padding(start = CheckMarkContentPadding)
                .clickable {
                    onNavigateToTermContent()
                }
        )
    }
}

private val CheckMarkSize = 24.dp
val CheckMarkContentPadding = 8.dp

@Preview(showBackground = true)
@Composable
fun CheckMarkWithTextUncheckedPreview() {
    val checkedState = remember { mutableStateOf(false) }

    CheckMarkWithText(
        modifier = Modifier.padding(16.dp),
        checkedState = checkedState.value,
        onCheckedChange = { newValue -> checkedState.value = newValue },
        content = "I agree to the terms and conditions",
        onNavigateToTermContent = {
            // Sample action when terms are clicked
            println("Navigate to terms and conditions content")
        }
    )
}

@Preview(showBackground = true)
@Composable
fun CheckMarkWithTextCheckedPreview() {
    val checkedState = remember { mutableStateOf(true) }

    CheckMarkWithText(
        modifier = Modifier.padding(16.dp),
        checkedState = checkedState.value,
        onCheckedChange = { newValue -> checkedState.value = newValue },
        content = "I agree to the terms and conditions",
        onNavigateToTermContent = {
            // Sample action when terms are clicked
            println("Navigate to terms and conditions content")
        }
    )
}