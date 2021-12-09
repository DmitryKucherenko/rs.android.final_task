package com.fatalzero.rsandroidfinal_task.presentation.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.fatalzero.rsandroidfinal_task.App
import com.fatalzero.rsandroidfinal_task.data.repository.JokesListRepositoryImpl
import com.fatalzero.rsandroidfinal_task.domain.JokeSendUseCase
import com.fatalzero.rsandroidfinal_task.domain.model.Joke
import com.fatalzero.rsandroidfinal_task.presentation.adapter.JokePagingSource
import javax.inject.Inject


class JokesListViewModel(application: Application) : ViewModel() {

    init {
        (application as App).appComponent.inject(this)
        println("view model!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
    }

    @Inject
    lateinit var repository: JokesListRepositoryImpl


    @Inject
    lateinit var jokeSendUseCase :JokeSendUseCase

    @Inject
    lateinit var  jokePagingSource:JokePagingSource

    val jokeFlowData = Pager(
        config = PagingConfig(pageSize = 1, enablePlaceholders = false),
        pagingSourceFactory = { jokePagingSource }
    ).flow

    fun sendJoke(joke: Joke?) {
        jokeSendUseCase.execute(joke)
    }


    class JokesListViewModelFactory(private val application: Application) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(JokesListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return JokesListViewModel(application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }


}

