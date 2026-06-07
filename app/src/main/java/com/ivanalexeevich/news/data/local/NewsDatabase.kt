package com.ivanalexeevich.news.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [ArticleDbModel::class, SubscriptionDbModel::class],
    version = 1,
    exportSchema = false
)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}