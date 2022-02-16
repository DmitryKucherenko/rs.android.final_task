package com.fatalzero.rsandroidfinal_task.presentation.JokeList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.fatalzero.rsandroidfinal_task.domain.usecase.JokeSaveUseCase
import com.fatalzero.rsandroidfinal_task.domain.usecase.JokeSendUseCase
import com.fatalzero.rsandroidfinal_task.domain.model.Joke
import com.fatalzero.rsandroidfinal_task.presentation.JokeList.adapter.JokePagingSource
import kotlinx.coroutines.launch
import javax.inject.Inject


class JokesListViewModel @Inject constructor(
    var jokeSendUseCase: JokeSendUseCase,
    var jokeSaveUseCase: JokeSaveUseCase,
    jokePagingSource: JokePagingSource
) : ViewModel() {

    init {
        Log.d("JokesListViewModel", "$this")
    }

    val jokeFlowData = Pager(
        config = PagingConfig(pageSize = 1, enablePlaceholders = false),
        pagingSourceFactory = { jokePagingSource }
    ).flow.cachedIn(viewModelScope)


    fun sendJoke(joke: Joke?) {
        jokeSendUseCase.execute(joke)
    }

    fun saveJoke(joke: Joke?) {
        viewModelScope.launch {
            Log.d("JOKE_LIST", joke.toString())
            jokeSaveUseCase(joke)
        }
    }


}

