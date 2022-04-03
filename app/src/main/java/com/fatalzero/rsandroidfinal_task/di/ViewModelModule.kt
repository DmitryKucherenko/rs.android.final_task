package com.fatalzero.rsandroidfinal_task.di

import com.fatalzero.rsandroidfinal_task.presentation.Fauvorite.AddViewModel
import com.fatalzero.rsandroidfinal_task.presentation.Fauvorite.FavouriteViewModel
import com.fatalzero.rsandroidfinal_task.presentation.JokeList.JokesListViewModel
import com.fatalzero.rsandroidfinal_task.presentation.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module



    val viewModelModule  = module {
        viewModel {
            MainActivityViewModel(get(),get())
        }
        viewModel {
            AddViewModel(get(),get())
        }
        viewModel {
            FavouriteViewModel(get(),get(),get(),get(),get())
        }
        viewModel{
            JokesListViewModel(get(),get(),get())
        }
    }











