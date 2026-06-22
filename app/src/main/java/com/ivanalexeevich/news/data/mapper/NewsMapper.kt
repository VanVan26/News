package com.ivanalexeevich.news.data.mapper

import android.annotation.SuppressLint
import com.ivanalexeevich.news.data.local.ArticleDbModel
import com.ivanalexeevich.news.data.remote.NewsResponseDto
import com.ivanalexeevich.news.domain.entity.Article
import com.ivanalexeevich.news.domain.settings.Interval
import com.ivanalexeevich.news.domain.settings.Language
import java.text.SimpleDateFormat
import java.util.Locale

fun NewsResponseDto.toDbModels(
    topic: String
): List<ArticleDbModel> {
    return articles.map {
        ArticleDbModel(
            title = it.title,
            description = it.description,
            imageUrl = it.urlToImage,
            sourceName = it.sourceDto.name,
            publishedAt = it.publishedAt.toTimeStamp(),
            url = it.url,
            topic = topic
        )
    }
}

fun Int.toInterval(): Interval {
    return Interval.entries.first { it.minutes == this }
}

fun Language.toApiRequest(): String {
    return when (this) {
        Language.ENGLISH -> "en"
        Language.RUSSIAN -> "ru"
        Language.FRENCH -> "fr"
        Language.DEUTSCH -> "de"
    }
}


fun List<ArticleDbModel>.toEntities(): List<Article> {
    return map {
        Article(
            title = it.title,
            description = it.description,
            imageUrl = it.imageUrl,
            sourceName = it.sourceName,
            publishedAt = it.publishedAt,
            url = it.url
        )
    }.distinct()
}

@SuppressLint("SimpleDateFormat")
private fun String.toTimeStamp(): Long {
    val dateFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    return dateFormatter.parse(this)?.time ?: System.currentTimeMillis()
}