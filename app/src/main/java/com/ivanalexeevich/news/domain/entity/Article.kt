package com.ivanalexeevich.news.domain.entity

import android.accessibilityservice.GestureDescription
import android.hardware.camera2.CameraExtensionSession

data class Article(
    val title: String,
    val description: String,
    val imageUrl: String?,
    val sourceName: String,
    val publishedAt: Long,
    val url: String
)
