package com.ivanalexeevich.news.data.mapper

import com.ivanalexeevich.news.domain.settings.RefreshConfig
import com.ivanalexeevich.news.domain.settings.Settings

fun Settings.toRefreshConfig(): RefreshConfig {
    return RefreshConfig(
        language = language,
        interval = interval,
        updateViaWifiEnabled = updateViaWifiEnabled
    )
}