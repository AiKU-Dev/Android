package com.aiku.presentation.ui.screen.signup.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.aiku.core.R
import com.aiku.core.theme.Headline_2G
import com.aiku.core.theme.Subtitle3
import com.aiku.core.theme.Subtitle3_SemiBold
import com.aiku.presentation.theme.Gray02
import com.aiku.presentation.theme.Green
import com.aiku.presentation.theme.Green5
import com.aiku.presentation.theme.ScreenBottomPadding
import com.aiku.presentation.theme.ScreenHorizontalPadding
import com.aiku.presentation.ui.component.background.CircularBackground
import com.aiku.presentation.ui.component.button.FullWidthButton
import com.aiku.presentation.ui.component.checkbox.CheckBoxWithText
import com.aiku.presentation.ui.component.checkbox.CheckMarkWithText

@Composable
fun TermsAgreementScreen(authNavController: NavHostController) {

    // CheckBox states
    var checkedStates by rememberSaveable { mutableStateOf(listOf(false, false, false, false)) }
    var checkedAll by rememberSaveable { mutableStateOf(checkedStates.all { it }) }

    val btnEnabled = checkedAll || checkedStates[0] && checkedStates[1] && checkedStates[2]

    val items = listOf(
        R.string.terms_agree_item1,
        R.string.terms_agree_item2,
        R.string.terms_agree_item3,
        R.string.terms_agree_item4
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 0.dp, bottom = ScreenBottomPadding)
            .padding(horizontal = ScreenHorizontalPadding)
    ) {

        Column {
            Row(
                modifier = Modifier.padding(top = ScreenTopPadding),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.size(TermsCharSize),
                    painter = painterResource(id = R.drawable.char_head_boy),
                    contentDescription = "char boy"
                )
                Image(
                    modifier = Modifier.size(18.dp, 14.dp),
                    painter = painterResource(id = R.drawable.ic_hi),
                    contentDescription = "text_hi"
                )
            }

            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = stringResource(id = R.string.terms_welcome_aiku),
                style = Headline_2G
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        CheckBoxWithText(
            modifier = Modifier.padding(bottom = TermsItemSpacing),
            checkedState = checkedAll,
            onCheckedChange = {
                isChecked ->
                checkedStates = checkedStates.map { isChecked }.toMutableStateList()
                checkedAll = isChecked
            },
            content = stringResource(id = R.string.terms_agree_all)
        )

        items.forEachIndexed { index, itemResId ->
            CheckMarkWithText(
                modifier = Modifier.padding(top = TermsItemSpacing),
                checkedState = checkedStates[index],
                onCheckedChange = {
                    checkedStates = checkedStates.toMutableList().apply {
                        set(index, it)
                    }
                    checkedAll = checkedStates.all { it }
                },
                content = stringResource(id = itemResId)
            )
        }


        FullWidthButton(
            modifier = Modifier.padding(top = ButtonContentSpacing),
            enabled = btnEnabled,
            background = ButtonDefaults.buttonColors(
                containerColor = Green5,
                disabledContainerColor = Gray02),
            onClick = {  },
            content = {
                Text(
                    text = stringResource(id = R.string.terms_agree_start),
                    style = Subtitle3_SemiBold,
                    color = Color.White
                )
            }
        )
    }
}

// dimens
private val ScreenTopPadding = 60.dp
private val TermsCharSize = 65.dp
private val TermsItemSpacing = 20.dp
private val ButtonContentSpacing = 45.dp


@Preview
@Composable
private fun TermsAgreementScreenPreview() {
    val authNavController = rememberNavController()
    TermsAgreementScreen(authNavController)
}
