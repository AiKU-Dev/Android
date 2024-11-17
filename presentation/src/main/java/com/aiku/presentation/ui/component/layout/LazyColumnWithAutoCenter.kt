package com.aiku.presentation.ui.component.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aiku.presentation.theme.Gray02
import com.aiku.presentation.theme.Typo
import kotlinx.coroutines.launch

@Composable
fun <T> LazyColumnWithAutoCenter(
    modifier: Modifier = Modifier,
    state: LazyListState,
    items: List<T>, // Any list of data
    onValueChange: (T) -> Unit,
    emptyStartItems: Int = 1, // Number of empty items at the start
    emptyEndItems: Int = 2    // Number of empty items at the end
) {
    val coroutineScope = rememberCoroutineScope()

    // Automatically select the centered item
    LaunchedEffect(state.isScrollInProgress) {
        if (!state.isScrollInProgress) {
            val centerIndex = state.firstVisibleItemIndex + 1 // Calculate the center item index
            val actualIndex = centerIndex - emptyStartItems // Adjust for empty items
            if (actualIndex in items.indices) { // Ensure the index is valid
                onValueChange(items[actualIndex])
                coroutineScope.launch {
                    state.animateScrollToItem(centerIndex - 1) // Fix the centered item
                }
            }
        }
    }

    Box(
        modifier = modifier
            .height(150.dp), // Restrict the height to show only the center and neighboring items
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            state = state,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Empty items at the start
            items(emptyStartItems) {
                Text(
                    text = "",
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                )
            }

            // Actual items
            items(items.size) { index ->
                val item = items[index]
                Text(
                    text = item.toString(), // Convert any item to a string for display
                    color = if (index + emptyStartItems == state.firstVisibleItemIndex + 1) Typo else Gray02,
                    modifier = Modifier.padding(vertical = 8.dp),
                    maxLines = 1
                )
            }

            // Empty items at the end
            items(emptyEndItems) {
                Text(
                    text = "",
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                )
            }
        }
    }
}