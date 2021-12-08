package com.fatalzero.rsandroidfinal_task.di

import android.content.Context
import com.fatalzero.rsandroidfinal_task.data.network.ApiService
import com.fatalzero.rsandroidfinal_task.data.repository.JokesListRepositoryImpl
import com.fatalzero.rsandroidfinal_task.domain.IJokesListUseCase
import com.fatalzero.rsandroidfinal_task.domain.JokeSendUseCase
import com.fatalzero.rsandroidfinal_task.domain.repository.JokesListRepository
import com.fatalzero.rsandroidfinal_task.domain.JokesListUseCase
import com.fatalzero.rsandroidfinal_task.presentation.adapter.JokePagingSource
import com.fatalzero.rsandroidfinal_task.utils.SendJokeImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class JokeListModule {
    @Singleton
    @Provides
    fun getRepository(jokesApiService: ApiService): JokesListRepository =
        JokesListRepositoryImpl(jokesApiService)

    @Singleton
    @Provides
    fun getListUseCase(repository: JokesListRepository): IJokesListUseCase =
        JokesListUseCase(repository)

    @Singleton
    @Provides
    fun getJokeSendUseCase(context: Context): JokeSendUseCase = JokeSendUseCase(SendJokeImpl(context))

    @Singleton
    @Provides
    fun getJokePagingSource(jokesListUseCase: IJokesListUseCase): JokePagingSource = JokePagingSource(jokesListUseCase)

}



