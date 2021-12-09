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


class JokesListViewModel @Inject constructor(var jokeSendUseCase: JokeSendUseCase,jokePagingSource:JokePagingSource) : ViewModel() {

      val jokeFlowData = Pager(
        config = PagingConfig(pageSize = 1, enablePlaceholders = false),
        pagingSourceFactory = { jokePagingSource }
    ).flow

    fun sendJoke(joke: Joke?) {
        jokeSendUseCase.execute(joke)
    }





}

