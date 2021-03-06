package com.fatalzero.rsandroidfinal_task.presentation

import androidx.lifecycle.ViewModel
import com.fatalzero.rsandroidfinal_task.domain.model.UserSettings
import com.fatalzero.rsandroidfinal_task.domain.usecase.GetSettingsUseCase
import com.fatalzero.rsandroidfinal_task.domain.usecase.SaveSettingsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainActivityViewModel(
    private var getSettingsUseCase: GetSettingsUseCase,
    private var saveSettingUseCase: SaveSettingsUseCase
) : ViewModel() {

    private val _isDarkTheme = MutableStateFlow(false)
    val isDarkTheme: StateFlow<Boolean> = _isDarkTheme.asStateFlow()

    fun saveSetting(isDarkTheme: Boolean) {
        val param = UserSettings(isDarkTheme)
        saveSettingUseCase.execute(param)
    }

    fun loadSettings() {
        val settings = getSettingsUseCase.execute()
        _isDarkTheme.value = settings.isDarkTheme
    }


}
