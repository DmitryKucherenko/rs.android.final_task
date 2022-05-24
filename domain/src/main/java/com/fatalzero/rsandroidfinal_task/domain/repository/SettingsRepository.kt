package com.fatalzero.rsandroidfinal_task.domain.repository

import com.fatalzero.rsandroidfinal_task.domain.model.UserSettings


interface SettingsRepository {
    fun saveSettings(userSettings: UserSettings): Boolean
    fun getSettings(): UserSettings
}
