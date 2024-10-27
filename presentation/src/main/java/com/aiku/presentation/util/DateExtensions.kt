package com.aiku.presentation.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

/**
 * ex) 2024.09.26 목 or 2024.09.26
 */
fun LocalDateTime.toDefaultDateFormat(withDayOfWeek: Boolean): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy. MM. dd", Locale.KOREA)
    return if (withDayOfWeek) {
        "${this.format(formatter)} ${this.getDayOfWeekKoreanShort()}"
    } else {
        this.format(formatter)
    }
}

/**
 * ex) 화
 */
fun LocalDateTime.getDayOfWeekKoreanShort(): String {
    return this.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREAN).substring(0, 1)
}

/**
 * ex) 오전 09:00
 */
fun LocalDateTime.to12TimeFormat(): String {
    val formatter = DateTimeFormatter.ofPattern("a hh:mm", Locale.KOREA)
    return this.format(formatter)
}
