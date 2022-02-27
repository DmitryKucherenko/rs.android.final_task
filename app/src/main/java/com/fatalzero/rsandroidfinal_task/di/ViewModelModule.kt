package com.fatalzero.rsandroidfinal_task.di

import androidx.lifecycle.ViewModel
import com.fatalzero.rsandroidfinal_task.presentation.Fauvorite.AddViewModel
import com.fatalzero.rsandroidfinal_task.presentation.Fauvorite.FavouriteViewModel
import com.fatalzero.rsandroidfinal_task.presentation.JokeList.JokesListViewModel
import com.fatalzero.rsandroidfinal_task.presentation.MainActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey


@Module
interface ViewModelModule {
    @IntoMap
    @StringKey("JokesListViewModel")
    @Binds
    fun bindListViewModel(impl: JokesListViewModel): ViewModel


    @IntoMap
    @StringKey("MainActivityViewModel")
    @Binds
    fun bindMainActivityViewModel(impl: MainActivityViewModel): ViewModel

    @IntoMap
    @StringKey("FavouriteViewModel")
    @Binds
    fun bindFauvorteViewModel(impl: FavouriteViewModel): ViewModel

    @IntoMap
    @StringKey("AddViewModel")
    @Binds
    fun bindAddViewModel(impl: AddViewModel): ViewModel

}
