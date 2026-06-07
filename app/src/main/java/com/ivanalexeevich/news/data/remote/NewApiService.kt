package com.ivanalexeevich.news.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface NewApiService {

    @GET("v2/everything?apiKey=09162344d6df40bba69746a3dc966dd1")
    suspend fun loadArticles(
        @Query("q") topic: String
    ): NewsResponseDto
}