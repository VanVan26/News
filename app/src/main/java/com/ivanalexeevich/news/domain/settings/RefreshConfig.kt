package com.ivanalexeevich.news.domain.settings

data class RefreshConfig(
    val language: Language,
    val interval: Interval,
    val updateViaWifiEnabled: Boolean
)
