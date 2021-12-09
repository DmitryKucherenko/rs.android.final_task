package com.fatalzero.rsandroidfinal_task.data.repository

import android.content.Context
import androidx.preference.PreferenceManager
import com.fatalzero.rsandroidfinal_task.domain.model.UserSettings
import com.fatalzero.rsandroidfinal_task.domain.repository.SettingsRepository
import javax.inject.Inject

private const val KEY_IS_DARK_THEME = "switch"

class SettingsRepositoryImpl @Inject constructor(var context: Context) : SettingsRepository {

    private val sharedPreferences by lazy { PreferenceManager.getDefaultSharedPreferences(context) }

    override fun saveSettings(userSettings: UserSettings): Boolean {
        sharedPreferences.edit().putBoolean(KEY_IS_DARK_THEME,userSettings.isDarkTheme).apply()
        return true
    }

    override fun getSettings(): UserSettings {
         val isDarkThemeSP = sharedPreferences.getBoolean(KEY_IS_DARK_THEME, false)
        return UserSettings(isDarkTheme = isDarkThemeSP)
    }
}
