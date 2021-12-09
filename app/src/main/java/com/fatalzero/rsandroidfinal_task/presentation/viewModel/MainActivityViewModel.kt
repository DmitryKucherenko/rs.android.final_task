package com.fatalzero.rsandroidfinal_task.presentation.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fatalzero.rsandroidfinal_task.App
import com.fatalzero.rsandroidfinal_task.data.repository.JokesListRepositoryImpl
import com.fatalzero.rsandroidfinal_task.data.repository.SettingsRepositoryImpl
import com.fatalzero.rsandroidfinal_task.domain.GetSettingsUseCase
import com.fatalzero.rsandroidfinal_task.domain.SaveSettingsUseCase
import com.fatalzero.rsandroidfinal_task.domain.model.UserSettings
import com.fatalzero.rsandroidfinal_task.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class MainActivityViewModel @Inject constructor (var getSettingsUseCase: GetSettingsUseCase,var saveSettingUseCase: SaveSettingsUseCase) :ViewModel() {




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



}
