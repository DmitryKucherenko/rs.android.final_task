package com.fatalzero.rsandroidfinal_task.data.repository

import android.content.Context
import androidx.preference.PreferenceManager
import com.fatalzero.rsandroidfinal_task.domain.model.UserSettings
import com.fatalzero.rsandroidfinal_task.domain.repository.SettingsRepository
import com.fatalzero.rsandroidfinal_task.utils.ShowMessage
import java.lang.Exception


private const val KEY_IS_DARK_THEME = "switch"

class SettingsRepositoryImpl(
    private var context: Context,
    private var showMessage: ShowMessage
) : SettingsRepository {


    private val sharedPreferences by lazy { PreferenceManager.getDefaultSharedPreferences(context) }

    override fun saveSettings(userSettings: UserSettings): Boolean {
        try {
            sharedPreferences.edit().putBoolean(KEY_IS_DARK_THEME, userSettings.isDarkTheme).apply()
            return true
        } catch (e: Exception) {
            showMessage(e.toString())
            throw e
        }
    }

    override fun getSettings(): UserSettings {
        try {
            val isDarkThemeSP = sharedPreferences.getBoolean(KEY_IS_DARK_THEME, false)
            return UserSettings(isDarkTheme = isDarkThemeSP)
        } catch (e: Exception) {
            showMessage(e.toString())
            throw e
        }
    }
}
