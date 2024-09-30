package com.aiku.presentation.navigation.route
import com.aiku.core.R

enum class BtmNavRoute {
    HOME,
    MYSCHEDULE,
    MYPAGE
}

sealed class BtmNav(val route: String, val labelId: Int, val icon: Int) {
    data object Home : BtmNav(BtmNavRoute.HOME.name, R.string.nav_home, R.drawable.btm_nav_home)
    data object MySchedule : BtmNav(BtmNavRoute.MYSCHEDULE.name, R.string.nav_mySchedule, R.drawable.btm_nav_schedule)
    data object My : BtmNav(BtmNavRoute.MYPAGE.name, R.string.nav_mypage, R.drawable.btm_nav_my)
}