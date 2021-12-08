package com.fatalzero.rsandroidfinal_task.presentation.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fatalzero.rsandroidfinal_task.data.repository.JokesListRepositoryImpl
import com.fatalzero.rsandroidfinal_task.data.repository.SettingsRepositoryImpl
import com.fatalzero.rsandroidfinal_task.domain.GetSettingsUseCase
import com.fatalzero.rsandroidfinal_task.domain.SaveSettingsUseCase
import com.fatalzero.rsandroidfinal_task.domain.model.UserSettings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainActivityViewModel (application: Application) :ViewModel() {
    var settingsRepository: SettingsRepositoryImpl = SettingsRepositoryImpl(application.applicationContext)
    private val getSettingsUseCase: GetSettingsUseCase = GetSettingsUseCase(settingsRepository)
    private val saveSettingUseCase: SaveSettingsUseCase = SaveSettingsUseCase(settingsRepository)



    private val _isDarkTheme = MutableStateFlow<Boolean>(false)
    val isDarkTheme: StateFlow<Boolean> = _isDarkTheme.asStateFlow()


    fun saveSetting(isDarkTheme: Boolean) {
        val param = UserSettings(isDarkTheme)
        saveSettingUseCase.execute(param)
    }

    fun loadSettings() {
        val settings = getSettingsUseCase.execute()
        _isDarkTheme.value = settings.isDarkTheme
    }


    class MainActivityViewModelFactory(private val application: Application) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainActivityViewModel(application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
