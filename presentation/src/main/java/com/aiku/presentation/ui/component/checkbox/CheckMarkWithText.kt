package com.aiku.presentation.ui.component.checkbox

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.aiku.core.R
import com.aiku.core.theme.Subtitle3
import com.aiku.presentation.navigation.route.SignUpRoute
import com.aiku.presentation.theme.Gray02
import com.aiku.presentation.theme.Green5
import com.aiku.presentation.theme.Typo

@Composable
fun CheckMarkWithText(
    modifier: Modifier,
    checkedState: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    identifier : Int,
    content: String,
    authNavController: NavHostController

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
                .clickable { authNavController.navigate("${SignUpRoute.TERM_CONTENT.name}/$identifier") }
        )
    }
}

private val CheckMarkSize = 24.dp
val CheckMarkContentPadding = 8.dp