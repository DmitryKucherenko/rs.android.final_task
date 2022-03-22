package com.fatalzero.rsandroidfinal_task.domain.usecase

import com.fatalzero.rsandroidfinal_task.domain.model.UserSettings
import com.fatalzero.rsandroidfinal_task.domain.repository.SettingsRepository
import javax.inject.Inject

class SaveSettingsUseCase @Inject constructor(private val settingsRepository: SettingsRepository) {

    fun execute(userSettings: UserSettings): Boolean {
        return settingsRepository.saveSettings(userSettings)
    }
}
