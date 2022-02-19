package com.fatalzero.rsandroidfinal_task.di

import android.content.Context
import com.fatalzero.rsandroidfinal_task.data.repository.JokesListRepositoryImpl
import com.fatalzero.rsandroidfinal_task.domain.usecase.IJokesListUseCase
import com.fatalzero.rsandroidfinal_task.domain.usecase.JokeSendUseCase
import com.fatalzero.rsandroidfinal_task.domain.repository.JokesListRepository
import com.fatalzero.rsandroidfinal_task.domain.usecase.JokesListUseCase
import com.fatalzero.rsandroidfinal_task.domain.usecase.SendJoke
import com.fatalzero.rsandroidfinal_task.presentation.JokeList.adapter.JokePagingSource
import com.fatalzero.rsandroidfinal_task.utils.SendJokeImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface JokeListModule {


    @Singleton
    @Binds
    fun bindJokeListRepository(jokesListRepositoryImpl: JokesListRepositoryImpl): JokesListRepository

    @Singleton
    @Binds
    fun bindJokeListUseCase(jokesListUseCase: JokesListUseCase): IJokesListUseCase

    @Singleton
    @Binds
    fun bindSendJoke(sendJokeImpl: SendJokeImpl): SendJoke


    @Singleton
    fun getJokeSendUseCase(context: Context): JokeSendUseCase

    @Singleton
    fun getJokePagingSource(jokesListUseCase: IJokesListUseCase): JokePagingSource
}



