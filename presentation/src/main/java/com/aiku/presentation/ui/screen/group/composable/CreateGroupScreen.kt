package com.aiku.presentation.ui.screen.group.composable

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text2.input.TextFieldLineLimits
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.aiku.core.R
import com.aiku.core.theme.Headline_3G
import com.aiku.core.theme.Subtitle3_SemiBold
import com.aiku.presentation.theme.Gray02
import com.aiku.presentation.theme.Green5
import com.aiku.presentation.theme.ScreenBottomPadding
import com.aiku.presentation.theme.ScreenHorizontalPadding
import com.aiku.presentation.theme.Typo
import com.aiku.presentation.ui.component.button.FullWidthButton
import com.aiku.presentation.ui.component.textfield.BottomLinedTextField
import com.aiku.presentation.ui.screen.group.viewmodel.CreateGroupUiState
import com.aiku.presentation.ui.screen.group.viewmodel.GroupViewModel
import coil.compose.rememberImagePainter

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CreateGroupScreen(
    navController : NavHostController,
    groupViewModel: GroupViewModel = hiltViewModel()
) {

    val groupNameInput by groupViewModel.groupNameInput.collectAsStateWithLifecycle()
    val isButtonEnabled by groupViewModel.isBtnEnabled.collectAsStateWithLifecycle()



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = ScreenHorizontalPadding)
            .padding(bottom = ScreenBottomPadding)
    ) {

        AsyncImage(
            model = "https://i.namu.wiki/i/ZnBMAAGJaiFKqDmASXCt-977Xuq6gLA-G8AsD4K1BKCVBEzrjISoW9QyfcSKPnacwuBpCGSSyBtCJv8E-UocNQ.webp",
            contentScale = ContentScale.Crop,
            contentDescription = "야호"
        )

        Text(
            modifier = Modifier.padding(top = 116.dp),
            text = stringResource(id = R.string.group_name),
            style = Headline_3G,
            color = Typo
        )

        BottomLinedTextField(
            value = groupNameInput,
            onValueChange = groupViewModel::onGroupNameInputChanged,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 38.dp),
            lineLimits = TextFieldLineLimits.SingleLine,
            showLengthIndicator = true,
            maxLength = GroupViewModel.MAX_GROUPNAME_LENGTH,
            placeholder = stringResource(id = R.string.group_name_setup_placeholder),
            label = stringResource(id = R.string.group_name_setup_label),
        )
        
        Spacer(modifier = Modifier.weight(1f))

        FullWidthButton(
            enabled = isButtonEnabled,
            background = ButtonDefaults.buttonColors(
                containerColor = Green5,
                disabledContainerColor = Gray02
            ),
            onClick = { groupViewModel.createGroup() },
            content = {
                Text(
                    text = stringResource(id = R.string.create),
                    style = Subtitle3_SemiBold,
                    color = Color.White
                )
            }
        )

        val createGroupUiState by groupViewModel.createGroupUiState.collectAsStateWithLifecycle()

        LaunchedEffect(createGroupUiState) {
            when (createGroupUiState) {
                CreateGroupUiState.Idle -> {
                    // TODO: Implement idle state handling
                }

                CreateGroupUiState.Loading -> {
                    Log.d("CreateGroupScreen", "Create Group Loading")
                    // TODO: Implement loading state handling
                }

                CreateGroupUiState.Success -> {
                    Log.d("CreateGroupScreen", "Create Group Success")
                    navController.popBackStack()
                }

                CreateGroupUiState.InvalidInput -> {
                    Log.d("CreateGroupScreen", "Create Group Invalid Input")
                    // TODO: Implement invalid input token handling
                }

                CreateGroupUiState.ServerError -> {
                    Log.d("CreateGroupScreen", "Create Group Server Error")
                    // TODO: Implement invalid input token handling
                }
            }
        }

    }
}