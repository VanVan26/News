package com.ivanalexeevich.news.domain.usecase

import android.adservices.topics.Topic
import com.ivanalexeevich.news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddSubscriptionUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(topic: String) {
        repository.addSubscription(topic)
        repository.updateArticlesForTopic(topic)
    }
}