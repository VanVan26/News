package com.ivanalexeevich.news.domain.usecase

import com.ivanalexeevich.news.data.mapper.toRefreshConfig
import com.ivanalexeevich.news.domain.repository.NewsRepository
import com.ivanalexeevich.news.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class StartRefreshDataUseCase @Inject constructor(
    private val newsRepository: NewsRepository,
    private val settingsRepository: SettingsRepository
)
{
    suspend operator fun invoke() {
        settingsRepository.getSettings()
            .map {
                it.toRefreshConfig()
            }
            .distinctUntilChanged()
            .onEach {
                newsRepository.startBackgroundRefresh(it)
            }.collect()

    }
}