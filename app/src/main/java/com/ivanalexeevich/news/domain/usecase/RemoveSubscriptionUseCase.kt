package com.ivanalexeevich.news.domain.usecase

import com.ivanalexeevich.news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoveSubscriptionUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(topic: String){
        return repository.removeSubscription(topic)
    }
}