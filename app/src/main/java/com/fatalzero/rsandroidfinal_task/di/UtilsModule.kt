package com.fatalzero.rsandroidfinal_task.di

import com.fatalzero.rsandroidfinal_task.domain.usecase.IJokesListUseCase
import com.fatalzero.rsandroidfinal_task.domain.usecase.JokesListUseCase
import com.fatalzero.rsandroidfinal_task.domain.usecase.SendJoke
import com.fatalzero.rsandroidfinal_task.domain.utils.SendJokeImpl
import com.fatalzero.rsandroidfinal_task.utils.dialog.DialogFactory
import com.fatalzero.rsandroidfinal_task.utils.dialog.DialogService
import org.koin.dsl.module

val utilsModule = module {
    single { DialogFactory(get()) }
    single { DialogService(get()) }
    factory<SendJoke> { SendJokeImpl(get()) }
}