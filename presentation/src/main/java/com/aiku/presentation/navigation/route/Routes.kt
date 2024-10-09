package com.aiku.presentation.navigation.route

object Routes {

    const val SPLASH = "splash"

    object Auth {
        const val LOGIN = "login"
        const val TERM_AGREEMENT = "termAgreement"
        const val TERM_CONTENT = "termContent"
        const val PROFILE_EDIT = "profileEdit"
    }

    object Main {
        object BtmNav {
            const val HOME = "home"
            const val MY_SCHEDULE = "mySchedule"
            const val MY_PAGE = "myPage"
        }
        object TopNav {
            const val NOTIFICATION = "notification"
            const val SHOP = "shop"
        }
    }

    object Group {
        const val GROUP = "group"
        const val SCHEDULE_WAITING = "scheduleWaiting"
        const val SCHEDULE_RUNNING = "scheduleRunning"

        object CreateSchedule {
            // TODO : 약속 생성 Route 추가
        }
    }
}