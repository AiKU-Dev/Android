package com.aiku.presentation.ui.group

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.aiku.core.R
import com.aiku.core.theme.Body2
import com.aiku.core.theme.Subtitle_4G
import com.aiku.presentation.state.group.MemberState
import com.aiku.presentation.theme.Gray02
import com.aiku.presentation.theme.Gray03
import com.aiku.presentation.theme.Green4
import com.aiku.presentation.ui.component.background.CircularBackground
import com.aiku.presentation.ui.component.chip.ProfileIcon
import com.aiku.presentation.ui.group.viewmodel.GroupUiState
import com.aiku.presentation.ui.type.GroupTabType
import kotlinx.coroutines.launch

private const val MEMBERS_PER_ROW = 3

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GroupTabRow(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    groupUiState: GroupUiState
) {

    val scope = rememberCoroutineScope()
    val scrollState = rememberLazyListState()
    val tabs = GroupTabType.entries

    Column(
        modifier = modifier
    ) {
        TabRow(
            modifier = Modifier.padding(horizontal = 20.dp),
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    height = 4.dp,
                    modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                    color = Green4
                )
            }
        ) {

            tabs.forEachIndexed { index, tab ->
                Tab(selected = pagerState.currentPage == index, onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }) {
                    Text(
                        modifier = Modifier.padding(vertical = 13.dp),
                        text = stringResource(tab.titleResId),
                        style = Subtitle_4G.copy(
                            color = if (pagerState.currentPage == index) Color.Black else Gray03
                        )
                    )
                }
            }

        }

        HorizontalPager(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            state = pagerState
        ) { index ->
            if (index == tabs.indexOf(GroupTabType.MEMBER)) {
                when (groupUiState) {
                    is GroupUiState.Success -> {
                        val groupState = groupUiState.group
                        GroupMembersView(
                            modifier = Modifier.fillMaxSize(),
                            scrollState = scrollState,
                            members = groupState.members
                        )
                    }

                    is GroupUiState.Loading -> {
                        // TODO 로딩뷰
                    }

                    is GroupUiState.Error -> {
                        // TODO 에러뷰
                    }
                }

            } else if (index == tabs.indexOf(GroupTabType.SCHEDULE)) {
                // TODO 약속뷰
            }
        }
    }
}

@Composable
fun GroupMembersView(
    modifier: Modifier = Modifier,
    scrollState: LazyListState,
    members: List<MemberState>
) {
    LazyColumn(
        modifier = modifier,
        state = scrollState
    ) {
        item {
            Row(
                modifier = Modifier.padding(top = 30.dp)
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularBackground(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        color = Gray02
                    ) {
                        Image(
                            modifier = Modifier,
                            painter = painterResource(id = R.drawable.char_man_1x),
                            contentDescription = stringResource(
                                id = R.string.invite_member
                            )
                        )
                    }
                    Text(
                        modifier = Modifier.padding(top = 5.dp),
                        text = stringResource(id = R.string.invite_member),
                        style = Body2.copy(
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
                MembersIconRow(
                    modifier = Modifier.weight(2f),
                    members = members.take(MEMBERS_PER_ROW - 1)
                )
            }
        }

        items(count = members.size / MEMBERS_PER_ROW) {
            MembersIconRow(
                modifier = Modifier.padding(top = 30.dp),
                members = members.subList(
                    (it + 1) * MEMBERS_PER_ROW - 1,
                    ((it + 2) * MEMBERS_PER_ROW - 1).coerceAtMost(members.size)
                ),
                adjustWeight = true
            )
        }
    }
}

@Composable
fun MembersIconRow(
    modifier: Modifier = Modifier,
    members: List<MemberState>,
    adjustWeight: Boolean = false
) {
    Row(
        modifier = modifier
    ) {
        members.forEach { member ->
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ProfileIcon(
                    modifier = Modifier
                        .padding(horizontal = 20.dp),
                    profile = member.profile
                )
                Text(
                    modifier = Modifier.padding(top = 5.dp),
                    text = member.nickname,
                    style = Body2.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
        }

        if (adjustWeight) {
            repeat(MEMBERS_PER_ROW - members.size) {
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}
