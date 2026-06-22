package com.ivanalexeevich.news.domain.usecase

import com.ivanalexeevich.news.domain.repository.NewsRepository
import com.ivanalexeevich.news.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class UpdateArticlesForAllSubscriptionsUseCase @Inject constructor(
    private val repository: NewsRepository,
    private val settingsRepository: SettingsRepository
) {
    suspend operator fun invoke(): List<String> {
        val language = settingsRepository.getSettings().first().language
        return repository.updateArticlesForAllSubscriptions(language)
    }
}