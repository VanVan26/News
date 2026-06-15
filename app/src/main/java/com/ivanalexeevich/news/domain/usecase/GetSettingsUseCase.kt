package com.ivanalexeevich.news.domain.usecase

import com.ivanalexeevich.news.domain.repository.SettingsRepository
import javax.inject.Inject

class GetSettingsUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository

) {
    operator fun invoke() = settingsRepository.getSettings()
}