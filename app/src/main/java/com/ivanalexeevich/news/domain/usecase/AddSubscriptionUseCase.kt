package com.ivanalexeevich.news.domain.usecase

import android.adservices.topics.Topic
import com.ivanalexeevich.news.domain.repository.NewsRepository
import com.ivanalexeevich.news.domain.repository.SettingsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

class AddSubscriptionUseCase @Inject constructor(
    private val repository: NewsRepository,
    private val settingsRepository: SettingsRepository
) {
    suspend operator fun invoke(topic: String) {
        repository.addSubscription(topic)
        CoroutineScope(currentCoroutineContext()).launch {
            val language = settingsRepository.getSettings().first().language
            repository.updateArticlesForTopic(topic, language)
        }
    }
}