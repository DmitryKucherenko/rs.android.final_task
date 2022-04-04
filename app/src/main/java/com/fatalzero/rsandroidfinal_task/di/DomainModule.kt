package com.fatalzero.rsandroidfinal_task.di

import com.fatalzero.rsandroidfinal_task.domain.usecase.*
import org.koin.dsl.module


val domainModule = module {
    factory { SearchUseCase(get()) }
    factory { SaveSettingsUseCase(get()) }
    factory { JokesListUseCase(get()) }
    factory { JokeSendUseCase(get()) }
    factory { JokeSaveUseCase(get()) }
    factory { JokeGetUseCase(get()) }
    factory { JokeFListUseCase(get()) }
    factory { JokeDeleteUseCase(get()) }
    factory { GetSettingsUseCase(get()) }
    factory<IJokesListUseCase> { JokesListUseCase(get()) }
}










