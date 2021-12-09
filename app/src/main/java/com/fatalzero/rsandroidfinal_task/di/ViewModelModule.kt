package com.fatalzero.rsandroidfinal_task.di

import androidx.lifecycle.ViewModel
import com.fatalzero.rsandroidfinal_task.presentation.viewModel.JokesListViewModel
import com.fatalzero.rsandroidfinal_task.presentation.viewModel.MainActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module
interface ViewModelModule {

    @IntoMap
    @StringKey ("JokesListViewModel")
    @Binds
    fun bindListViewModel(impl: JokesListViewModel): ViewModel

    @IntoMap
    @StringKey ("MainActivityViewModel")
    @Binds
    fun bindMainActivityViewModel(impl: MainActivityViewModel): ViewModel

}
