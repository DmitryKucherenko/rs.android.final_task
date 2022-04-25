package com.fatalzero.rsandroidfinal_task.presentation.JokeList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fatalzero.rsandroidfinal_task.domain.model.Filters
import com.fatalzero.rsandroidfinal_task.domain.usecase.JokeSaveUseCase
import com.fatalzero.rsandroidfinal_task.domain.usecase.JokeSendUseCase
import com.fatalzero.rsandroidfinal_task.domain.model.Joke
import com.fatalzero.rsandroidfinal_task.domain.usecase.AddFilterUseCase
import com.fatalzero.rsandroidfinal_task.presentation.JokeList.adapter.JokePagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class JokesListViewModel (
    private var jokeSendUseCase: JokeSendUseCase,
    private var jokeSaveUseCase: JokeSaveUseCase,
    private var jokePagingSource: JokePagingSource,
    private var filterUseCase: AddFilterUseCase
) : ViewModel() {

    var jokeFlowData: Flow<PagingData<Joke>>? = null
    init {
        update()
    }

    fun update(){
        jokeFlowData = Pager(
            config = PagingConfig(pageSize = 1, enablePlaceholders = false),
            pagingSourceFactory = { jokePagingSource }
        ).flow.cachedIn(viewModelScope)
    }

    fun sendJoke(joke: Joke?) {
        jokeSendUseCase.execute(joke)
    }

    fun saveJoke(joke: Joke?) {
        viewModelScope.launch {
            Log.d("JOKE_LIST", joke.toString())
            jokeSaveUseCase(joke)
        }
    }

    fun addFilter(filter: Filters){
        Log.d("REPO","addFilter")
        filterUseCase(filter)
        update()
    }


}

