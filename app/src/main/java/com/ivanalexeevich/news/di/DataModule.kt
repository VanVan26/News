package com.ivanalexeevich.news.di

import android.content.Context
import androidx.annotation.UiContext
import androidx.room.Room
import com.ivanalexeevich.news.data.local.NewsDao
import com.ivanalexeevich.news.data.local.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {


    companion object{


        @Provides
        @Singleton
        fun provideNewsDataBase(
            @ApplicationContext context: Context
        ): NewsDatabase{
            return Room.databaseBuilder(
                context = context,
                klass = NewsDatabase::class.java,
                name = "news.db",
            ).fallbackToDestructiveMigration(true).build()
        }

        @Provides
        @Singleton
        fun provideNewsDao(
            database: NewsDatabase
        ): NewsDao{
            return database.newsDao()
        }
    }
}