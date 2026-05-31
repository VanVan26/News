package com.ivanalexeevich.news.domain.usecase

import com.ivanalexeevich.news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateArticlesForAllSubscriptionsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(){
        repository.updateArticlesForAllSubscriptions()
    }
}