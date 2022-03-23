package com.fatalzero.rsandroidfinal_task.domain.usecase

import com.fatalzero.rsandroidfinal_task.domain.model.UserSettings
import com.fatalzero.rsandroidfinal_task.domain.repository.SettingsRepository

class SaveSettingsUseCase (private val settingsRepository: SettingsRepository) {

    fun execute(userSettings: UserSettings): Boolean {
        return settingsRepository.saveSettings(userSettings)
    }
}
