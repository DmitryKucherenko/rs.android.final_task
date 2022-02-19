package com.fatalzero.rsandroidfinal_task.di

import com.fatalzero.rsandroidfinal_task.data.repository.SettingsRepositoryImpl
import com.fatalzero.rsandroidfinal_task.domain.repository.SettingsRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface MainActivityModule {
    @Singleton
    @Binds
    fun bindsSettingRepository(settingsRepositoryImpl: SettingsRepositoryImpl): SettingsRepository
}
