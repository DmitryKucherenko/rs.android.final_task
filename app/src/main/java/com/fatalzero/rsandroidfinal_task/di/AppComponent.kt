package com.fatalzero.rsandroidfinal_task.di

import android.app.Application
import android.content.Context
import com.fatalzero.rsandroidfinal_task.data.network.ApiFactory
import com.fatalzero.rsandroidfinal_task.presentation.JokesList
import com.fatalzero.rsandroidfinal_task.presentation.MainActivity
import com.fatalzero.rsandroidfinal_task.presentation.viewModel.JokesListViewModel
import com.fatalzero.rsandroidfinal_task.presentation.viewModel.MainActivityViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [JokeListModule::class, NetWorkModule::class, MainActivityModule::class, ViewModelModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context
        ): AppComponent
    }


    fun inject(apiFactory: ApiFactory)
    fun inject(mainActivity: MainActivity)
    fun inject(jokesList: JokesList)


}
