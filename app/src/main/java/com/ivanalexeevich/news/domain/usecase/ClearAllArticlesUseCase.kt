package com.ivanalexeevich.news.domain.usecase

import android.adservices.topics.Topic
import com.ivanalexeevich.news.domain.entity.Article
import com.ivanalexeevich.news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ClearAllArticlesUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(topics: List<String>){
        repository.clearAllArticles(topics)
    }
}