package com.fatalzero.rsandroidfinal_task.di

import androidx.lifecycle.ViewModel
import com.fatalzero.rsandroidfinal_task.presentation.Fauvorite.AddViewModel
import com.fatalzero.rsandroidfinal_task.presentation.Fauvorite.FauvorteViewModel
import com.fatalzero.rsandroidfinal_task.presentation.JokeList.JokesListViewModel
import com.fatalzero.rsandroidfinal_task.presentation.MainActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey
import javax.inject.Singleton


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
    @StringKey("FauvorteViewModel")
    @Binds
    fun bindFauvorteViewModel(impl: FauvorteViewModel): ViewModel

    @IntoMap
    @StringKey("AddViewModel")
    @Binds
    fun bindAddViewModel(impl: AddViewModel): ViewModel

}
