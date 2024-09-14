package com.aiku.presentation.navigation.route
import com.aiku.core.R

enum class BtmNavRoute {
    HOME,
    MYSCHEDULE,
    MYPAGE
}

sealed class BtmNav(val route: String, val label: String, val icon: Int) {
    data object Home : BtmNav(BtmNavRoute.HOME.name, "홈", R.drawable.btm_nav_home)
    data object MySchedule : BtmNav(BtmNavRoute.MYSCHEDULE.name, "내 약속", R.drawable.btm_nav_schedule)
    data object My : BtmNav(BtmNavRoute.MYPAGE.name, "마이", R.drawable.btm_nav_my)
}