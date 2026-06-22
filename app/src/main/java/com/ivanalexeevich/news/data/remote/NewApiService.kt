package com.ivanalexeevich.news.data.remote

import com.ivanalexeevich.news.BuildConfig
import com.ivanalexeevich.news.domain.settings.Language
import retrofit2.http.GET
import retrofit2.http.Query

interface NewApiService {

    @GET("v2/everything?apiKey=${BuildConfig.NEWS_API_KEY}")
    suspend fun loadArticles(
        @Query("q") topic: String,
        @Query("language") language: String
    ): NewsResponseDto
}