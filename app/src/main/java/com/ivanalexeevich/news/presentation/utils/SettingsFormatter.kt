package com.ivanalexeevich.news.presentation.utils

import androidx.annotation.StringRes
import com.ivanalexeevich.news.R
import com.ivanalexeevich.news.domain.settings.Interval
import com.ivanalexeevich.news.domain.settings.Language

fun Language.titleString(): String {
    return when (this) {
        Language.ENGLISH -> "English"
        Language.RUSSIAN -> "Русский"
        Language.FRENCH -> "Français"
        Language.DEUTSCH -> "Deutsch"
    }
}

@StringRes
fun Interval.titleString(): Int{
    return when (this){
        Interval.MIN_15 -> {
            R.string._15_minutes
        }
        Interval.MIN_30 -> {
            R.string._30_minutes
        }
        Interval.HOUR_1 -> {
            R.string._1_hour
        }
    }
}