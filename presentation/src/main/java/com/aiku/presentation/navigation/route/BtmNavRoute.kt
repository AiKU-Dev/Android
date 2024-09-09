package com.aiku.presentation.navigation.route
import com.aiku.core.R

sealed class BtmNav(val route: String, val label: String, val icon: Int) {
    data object MySchedule : BtmNav("mySchedule", "내 약속", R.drawable.btm_nav_schedule)
    data object Home : BtmNav("home", "홈", R.drawable.btm_nav_home)
    data object My : BtmNav("my", "마이", R.drawable.btm_nav_my)
}