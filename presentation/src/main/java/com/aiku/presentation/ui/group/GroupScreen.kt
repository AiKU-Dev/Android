package com.aiku.presentation.ui.group

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aiku.presentation.ui.group.viewmodel.GroupViewModel
import com.aiku.presentation.ui.type.GroupTabType

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GroupScreen(
    modifier: Modifier = Modifier,
    groupId: Long,
    viewModel: GroupViewModel = hiltViewModel(
        creationCallback = { factory: GroupViewModel.Factory ->
            factory.create(groupId)
        }
    )
) {
    val pagerState = rememberPagerState { GroupTabType.entries.size }

    val groupUiState by viewModel.group.collectAsStateWithLifecycle()

    Column(
        modifier = modifier,
    ) {
        // TODO 광고 배너
        GroupTabRow(
            modifier = Modifier.fillMaxSize(),
            pagerState = pagerState,
            groupUiState = groupUiState
        )
    }
}