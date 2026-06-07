package com.ivanalexeevich.news.data.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleDto(
    @SerialName("description")
    val description: String = "",
    @SerialName("publishedAt")
    val publishedAt: String = "",
    @SerialName("source")
    val sourceDto: SourceDto = SourceDto(),
    @SerialName("title")
    val title: String = "",
    @SerialName("url")
    val url: String = "",
    @SerialName("urlToImage")
    val urlToImage: String? = ""
)