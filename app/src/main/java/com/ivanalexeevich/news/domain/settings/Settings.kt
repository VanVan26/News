package com.ivanalexeevich.news.domain.settings

import android.app.Notification
import android.content.pm.PackageManager

data class Settings(
    val language: Language,
    val interval: Interval,
    val notificationsEnabledSetting: Boolean,
    val updateViaWifiEnabled: Boolean
    )

enum class Language {
    ENGLISH, RUSSIAN, FRENCH, DEUTSCH
}

enum class Interval(val minutes: Int) {
    MIN_15(15), MIN_30(30), HOUR_1(60)
}