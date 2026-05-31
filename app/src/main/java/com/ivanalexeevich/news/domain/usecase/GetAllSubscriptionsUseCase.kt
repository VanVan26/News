package com.ivanalexeevich.news.domain.usecase

import com.ivanalexeevich.news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllSubscriptionsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke() : Flow<List<String>>{
        return repository.getAllSubscriptions()
    }
}