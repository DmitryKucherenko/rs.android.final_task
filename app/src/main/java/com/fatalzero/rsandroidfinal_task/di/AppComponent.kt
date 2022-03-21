package com.fatalzero.rsandroidfinal_task.di

import android.content.Context
import com.fatalzero.rsandroidfinal_task.presentation.Fauvorite.AddFragment
import com.fatalzero.rsandroidfinal_task.presentation.Fauvorite.FavouriteListFragment
import com.fatalzero.rsandroidfinal_task.presentation.JokeList.JokesList
import com.fatalzero.rsandroidfinal_task.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class, PresentationModule::class, DomainModule::class,DataModule::class,DataModuleBinds::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context
        ): AppComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(jokesList: JokesList)
    fun inject(favouriteListFragment: FavouriteListFragment)
    fun inject(addFragment: AddFragment)
}
