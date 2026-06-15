package com.ivanalexeevich.news.domain.repository

import com.ivanalexeevich.news.domain.settings.Language
import com.ivanalexeevich.news.domain.settings.Settings
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {

    fun getSettings(): Flow<Settings>


    suspend fun switchNotifications(enabled: Boolean)
    suspend fun updateLanguage(language: Language)
    suspend fun updateInterval(minutes: Int)
    suspend fun switchWifiOnly(wifiOnly: Boolean)

}