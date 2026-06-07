package com.ivanalexeevich.news.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.ivanalexeevich.news.domain.entity.Article
import kotlinx.coroutines.flow.Flow


@Dao
interface NewsDao {

    @Query("SELECT * FROM subscriptions")
    fun getAllSubscriptions(): Flow<List<SubscriptionDbModel>>

    @Upsert
    suspend fun addSubscription(subscriptionDbModel: SubscriptionDbModel)

    @Transaction
    @Delete
    suspend fun removeSubscription(subscriptionDbModel: SubscriptionDbModel)

    @Query("SELECT * FROM articles WHERE articles.topic IN (:topics) ORDER BY publishedAt DESC")
    fun getArticlesByTopics(topics: List<String>): Flow<List<ArticleDbModel>>

    @Upsert
    suspend fun addArticles(articles: List<ArticleDbModel>)

    @Query("DELETE FROM articles WHERE articles.topic IN (:topics)")
    suspend fun deleteArticlesByTopics(topics: List<String>)

}