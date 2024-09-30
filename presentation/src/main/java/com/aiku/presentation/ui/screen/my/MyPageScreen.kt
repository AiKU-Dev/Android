package com.aiku.presentation.ui.screen.my

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.aiku.presentation.ui.screen.base.BaseScaffold

@Composable
fun MyPageScreen(navController: NavHostController) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "마이페이지 화면")
        }

}

@Preview(showBackground = true)
@Composable
fun MyScreenPreview() {
    val navController = rememberNavController()
    MyPageScreen(navController = navController)
}