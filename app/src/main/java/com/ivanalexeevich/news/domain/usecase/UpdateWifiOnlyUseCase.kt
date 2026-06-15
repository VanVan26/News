package com.ivanalexeevich.news.domain.usecase

import com.ivanalexeevich.news.domain.repository.SettingsRepository
import javax.inject.Inject

class UpdateWifiOnlyUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository

) {
    suspend operator fun invoke(isEnable: Boolean) = settingsRepository.switchWifiOnly(isEnable)
}