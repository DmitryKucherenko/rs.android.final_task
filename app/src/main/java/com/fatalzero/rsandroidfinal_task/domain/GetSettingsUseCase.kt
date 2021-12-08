package com.fatalzero.rsandroidfinal_task.domain

import com.fatalzero.rsandroidfinal_task.domain.model.UserSettings
import com.fatalzero.rsandroidfinal_task.domain.repository.SettingsRepository

class GetSettingsUseCase (private val settingsRepository: SettingsRepository) {

    fun execute(): UserSettings  {
        return settingsRepository.getSettings()
    }
}
