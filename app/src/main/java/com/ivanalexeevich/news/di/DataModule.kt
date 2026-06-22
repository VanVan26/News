package com.ivanalexeevich.news.di

import android.content.Context
import androidx.room.Room
import androidx.work.WorkManager
import com.ivanalexeevich.news.data.local.NewsDao
import com.ivanalexeevich.news.data.local.NewsDatabase
import com.ivanalexeevich.news.data.remote.NewApiService
import com.ivanalexeevich.news.data.repository.NewsRepositoryImpl
import com.ivanalexeevich.news.data.repository.SettingsRepositoryImpl
import com.ivanalexeevich.news.domain.repository.NewsRepository
import com.ivanalexeevich.news.domain.repository.SettingsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {


    @Binds
    @Singleton
    fun bindNewsRepository(
        impl: NewsRepositoryImpl
    ) : NewsRepository

    @Binds
    @Singleton
    fun bindSettingsRepository(
        impl: SettingsRepositoryImpl
    ): SettingsRepository

    companion object {

        @Provides
        @Singleton
        fun provideWorkManager(
            @ApplicationContext context: Context
        ): WorkManager = WorkManager.getInstance(context)

        @Provides
        @Singleton
        fun provideJson():Json{
            return Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            }
        }

        @Provides
        @Singleton
        fun provideConverterFactory(
            json: Json
        ) : Converter.Factory{
            return json.asConverterFactory(
                "application/json".toMediaType()
            )
        }
        @Provides
        @Singleton
        fun provideRetrofit(
            converter: Converter.Factory
        ) : Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://newsapi.org/")
                .addConverterFactory(converter)
                .build()
        }


        @Provides
        @Singleton
        fun provideApiService(
            retrofit : Retrofit
        ): NewApiService {
            return retrofit.create()
        }


        @Provides
        @Singleton
        fun provideNewsDataBase(
            @ApplicationContext context: Context
        ): NewsDatabase {
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
        ): NewsDao {
            return database.newsDao()
        }
    }
}