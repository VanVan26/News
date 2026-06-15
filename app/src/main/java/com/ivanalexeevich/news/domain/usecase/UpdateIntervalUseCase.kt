package com.ivanalexeevich.news.domain.usecase

import com.ivanalexeevich.news.domain.repository.SettingsRepository
import javax.inject.Inject

class UpdateIntervalUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository

) {
    suspend operator fun invoke(minutes: Int) = settingsRepository.updateInterval(minutes)
}