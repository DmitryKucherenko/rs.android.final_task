package com.fatalzero.rsandroidfinal_task.di




import com.fatalzero.rsandroidfinal_task.data.repository.JokesListRepositoryImpl
import com.fatalzero.rsandroidfinal_task.data.repository.SettingsRepositoryImpl
import com.fatalzero.rsandroidfinal_task.domain.repository.JokesListRepository
import com.fatalzero.rsandroidfinal_task.domain.repository.SettingsRepository
import com.fatalzero.rsandroidfinal_task.domain.usecase.IJokesListUseCase
import com.fatalzero.rsandroidfinal_task.domain.usecase.JokesListUseCase
import com.fatalzero.rsandroidfinal_task.domain.usecase.SendJoke
import com.fatalzero.rsandroidfinal_task.domain.utils.SendJokeImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DataModuleBinds {

    @Singleton
    @Binds
    fun bindJokeListRepository(jokesListRepositoryImpl:
                               JokesListRepositoryImpl
    ): JokesListRepository

    @Singleton
    @Binds
    fun bindJokeListUseCase(jokesListUseCase: JokesListUseCase): IJokesListUseCase

    @Singleton
    @Binds
    fun bindSendJoke(sendJokeImpl: SendJokeImpl): SendJoke


    @Singleton
    @Binds
    fun bindsSettingRepository(settingsRepositoryImpl: SettingsRepositoryImpl): SettingsRepository


}



