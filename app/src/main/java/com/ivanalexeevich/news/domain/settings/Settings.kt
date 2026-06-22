package com.ivanalexeevich.news.domain.settings

data class Settings(
    val language: Language,
    val interval: Interval,
    val notificationsEnabled: Boolean,
    val updateViaWifiEnabled: Boolean
    )
{
    companion object {
        val DEFAULT_LANGUAGE = Language.RUSSIAN
        val DEFAULT_INTERVAL = Interval.MIN_30
        const val DEFAULT_NOTIFICATIONS_ENABLED = false
        const val DEFAULT_WIFI_ONLY = false

    }
}

enum class Language {
    ENGLISH, RUSSIAN, FRENCH, DEUTSCH
}

enum class Interval(val minutes: Int) {
    MIN_15(15), MIN_30(30), HOUR_1(60)
}