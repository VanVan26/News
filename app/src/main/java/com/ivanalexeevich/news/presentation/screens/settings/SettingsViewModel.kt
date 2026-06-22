package com.ivanalexeevich.news.presentation.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivanalexeevich.news.domain.settings.Interval
import com.ivanalexeevich.news.domain.settings.Language
import com.ivanalexeevich.news.domain.settings.Settings
import com.ivanalexeevich.news.domain.usecase.GetSettingsUseCase
import com.ivanalexeevich.news.domain.usecase.UpdateIntervalUseCase
import com.ivanalexeevich.news.domain.usecase.UpdateLanguageUseCase
import com.ivanalexeevich.news.domain.usecase.UpdateNotificationsEnabledUseCase
import com.ivanalexeevich.news.domain.usecase.UpdateWifiOnlyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getSettingsUseCase: GetSettingsUseCase,
    private val updateIntervalUseCase: UpdateIntervalUseCase,
    private val updateNotificationsEnabledUseCase: UpdateNotificationsEnabledUseCase,
    private val updateWifiOnlyUseCase: UpdateWifiOnlyUseCase,
    private val updateLanguageUseCase: UpdateLanguageUseCase
) : ViewModel() {


    private val _state = MutableStateFlow<SettingsState>(SettingsState())


    val state = _state.asStateFlow()

    fun processCommand(command: SettingsCommand) {
        when (command) {
            is SettingsCommand.SelectInterval -> {
                viewModelScope.launch {
                    _state.update { previousState ->
                        updateIntervalUseCase(command.interval.minutes)
                        previousState.copy(selectedInterval = command.interval)
                    }
                }
            }

            is SettingsCommand.SelectLanguage -> {
                viewModelScope.launch {
                    _state.update { previousState ->
                        updateLanguageUseCase(command.language)
                        previousState.copy(selectedLanguage = command.language)
                    }
                }

            }

            SettingsCommand.SwitchNotification -> {
                viewModelScope.launch {
                    _state.update { previousState ->
                        updateNotificationsEnabledUseCase(!previousState.isNotificationEnabled)
                        previousState.copy(isNotificationEnabled = !previousState.isNotificationEnabled)
                    }
                }
            }

            SettingsCommand.SwitchWifiOnly -> {
                viewModelScope.launch {
                    _state.update { previousState ->
                        updateWifiOnlyUseCase(!previousState.isWifiOnlyEnabled)
                        previousState.copy(isWifiOnlyEnabled = !previousState.isWifiOnlyEnabled)
                    }
                }
            }
        }
    }

    private fun observeSettings() {
        viewModelScope.launch {
            _state.update { previousState ->
                val settings = getSettingsUseCase().first()
                previousState.copy(
                    selectedLanguage = settings.language,
                    selectedInterval = settings.interval,
                    isNotificationEnabled = settings.notificationsEnabled,
                    isWifiOnlyEnabled = settings.updateViaWifiEnabled
                )
            }
        }
    }
    init {
        observeSettings()
    }
}

sealed interface SettingsCommand {
    object SwitchNotification : SettingsCommand
    object SwitchWifiOnly : SettingsCommand
    data class SelectLanguage(val language: Language) : SettingsCommand
    data class SelectInterval(val interval: Interval) : SettingsCommand
}

data class SettingsState(
    val selectedLanguage: Language = Settings.DEFAULT_LANGUAGE,
    val selectedInterval: Interval = Settings.DEFAULT_INTERVAL,
    val isNotificationEnabled: Boolean = Settings.DEFAULT_NOTIFICATIONS_ENABLED,
    val isWifiOnlyEnabled: Boolean = Settings.DEFAULT_WIFI_ONLY,
    val languages: List<Language> = Language.entries,
    val intervals: List<Interval> = Interval.entries
) {
}

