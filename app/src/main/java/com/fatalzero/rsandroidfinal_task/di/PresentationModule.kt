package com.fatalzero.rsandroidfinal_task.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fatalzero.rsandroidfinal_task.domain.usecase.*
import com.fatalzero.rsandroidfinal_task.domain.utils.SendJokeImpl
import com.fatalzero.rsandroidfinal_task.presentation.Fauvorite.AddViewModel
import com.fatalzero.rsandroidfinal_task.presentation.Fauvorite.FavouriteViewModel
import com.fatalzero.rsandroidfinal_task.presentation.JokeList.JokesListViewModel
import com.fatalzero.rsandroidfinal_task.presentation.JokeList.adapter.JokePagingSource
import com.fatalzero.rsandroidfinal_task.presentation.MainActivityViewModel
import com.fatalzero.rsandroidfinal_task.utils.ShowMessage
import com.fatalzero.rsandroidfinal_task.utils.ViewModelFactory
import com.fatalzero.rsandroidfinal_task.utils.dialog.DialogService
import dagger.Module
import dagger.Provides


@Module
class PresentationModule {

    @Provides
    fun provideMainActivityViewModel(
        getSettingsUseCase: GetSettingsUseCase,
        saveSettingUseCase: SaveSettingsUseCase
    ): MainActivityViewModel {
        return MainActivityViewModel(getSettingsUseCase, saveSettingUseCase)
    }

    @Provides
    fun provideAddViewModel(
        saveUseCase: JokeSaveUseCase,
        jokeGetUseCase: JokeGetUseCase
    ): AddViewModel {
        return AddViewModel(saveUseCase, jokeGetUseCase)
    }

    @Provides
    fun provideFavouriteViewModel(
        jokeFListUseCase: JokeFListUseCase,
        jokeDeleteUseCase: JokeDeleteUseCase,
        jokeSearchUseCase: SearchUseCase,
        jokeSendUseCase: JokeSendUseCase,
        dialogService: DialogService
    ): FavouriteViewModel {
        return FavouriteViewModel(
            jokeFListUseCase,
            jokeDeleteUseCase,
            jokeSearchUseCase,
            jokeSendUseCase,
            dialogService
        )
    }


        @Provides
        fun provideJokePagingSource(jokesListUseCase: IJokesListUseCase): JokePagingSource {
            return JokePagingSource(jokesListUseCase)
        }

        @Provides
        fun provideJokesListViewModel(
            jokeSendUseCase: JokeSendUseCase,
            jokeSaveUseCase: JokeSaveUseCase,
            jokePagingSource: JokePagingSource
        ): JokesListViewModel {
            return JokesListViewModel(jokeSendUseCase, jokeSaveUseCase, jokePagingSource)
        }

        @Provides
        fun provideSendJokeImpl(context: Context): SendJokeImpl {
            return SendJokeImpl(context)
        }

        @Provides
        fun provideShowMessage(context: Context): ShowMessage {
            return ShowMessage(context)
        }




}