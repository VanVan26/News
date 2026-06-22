package com.ivanalexeevich.news.domain.repository

import com.ivanalexeevich.news.domain.entity.Article
import com.ivanalexeevich.news.domain.settings.Language
import com.ivanalexeevich.news.domain.settings.RefreshConfig
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun startBackgroundRefresh(refreshConfig: RefreshConfig)

    fun getAllSubscriptions(): Flow<List<String>>

    suspend fun addSubscription(topic: String)

    suspend fun updateArticlesForTopic(topic: String, language: Language): Boolean

    suspend fun removeSubscription(topic: String)

    suspend fun updateArticlesForAllSubscriptions(language: Language) : List<String>

    fun getArticlesByTopics(topics: List<String>): Flow<List<Article>>

    suspend fun clearAllArticles(topics: List<String>)
}