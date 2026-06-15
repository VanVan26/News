package com.ivanalexeevich.news.domain.usecase

import com.ivanalexeevich.news.domain.repository.SettingsRepository
import com.ivanalexeevich.news.domain.settings.Language
import javax.inject.Inject

class UpdateLanguageUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository

) {
    suspend operator fun invoke(language: Language) = settingsRepository.updateLanguage(language)
}