package com.fatalzero.rsandroidfinal_task.di

import com.fatalzero.rsandroidfinal_task.domain.repository.JokesListRepository
import com.fatalzero.rsandroidfinal_task.domain.repository.SettingsRepository
import com.fatalzero.rsandroidfinal_task.domain.usecase.*
import com.fatalzero.rsandroidfinal_task.utils.dialog.DialogService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DomainModule {

    @Provides
    fun provideSearchUseCase(jokeListRepository: JokesListRepository):SearchUseCase{
        return SearchUseCase(jokeListRepository)
    }
    @Provides
    fun provideSaveSettingsUseCase(settingsRepository: SettingsRepository):SaveSettingsUseCase{
        return SaveSettingsUseCase(settingsRepository)
    }

    @Provides
    fun provideJokesListUseCase(jokeListRepository: JokesListRepository): JokesListUseCase {
        return JokesListUseCase(jokeListRepository)
    }
    @Provides
    fun provideJokeSendUseCase(sendJokeService: SendJoke): JokeSendUseCase {
        return JokeSendUseCase(sendJokeService)
    }
    @Provides
    fun provideJokeSaveUseCase(jokeListRepository: JokesListRepository):JokeSaveUseCase{
        return JokeSaveUseCase(jokeListRepository)
    }
    @Provides
    fun provideJokeGetUseCase(jokeListRepository: JokesListRepository):JokeGetUseCase{
        return JokeGetUseCase(jokeListRepository)
    }
    @Provides
    fun provideJokeFListUseCase(jokeListRepository: JokesListRepository):JokeFListUseCase{
        return JokeFListUseCase(jokeListRepository)
    }
    @Provides
    fun provideJokeDeleteUseCase(jokeListRepository: JokesListRepository):JokeDeleteUseCase{
        return JokeDeleteUseCase(jokeListRepository)
    }
    @Provides
    fun provideGetSettingsUseCase(settingsRepository: SettingsRepository):GetSettingsUseCase{
        return GetSettingsUseCase(settingsRepository)
    }



}