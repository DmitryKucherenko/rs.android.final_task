package com.fatalzero.rsandroidfinal_task.di

import com.fatalzero.rsandroidfinal_task.domain.utils.SendJokeImpl
import com.fatalzero.rsandroidfinal_task.presentation.JokeList.adapter.JokePagingSource
import com.fatalzero.rsandroidfinal_task.utils.ShowMessage
import org.koin.dsl.module

val presentationModule = module {
    factory { JokePagingSource(get()) }
    factory { SendJokeImpl(get()) }
    factory { ShowMessage(get()) }
}




