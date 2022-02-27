package com.fatalzero.rsandroidfinal_task.presentation.Fauvorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fatalzero.rsandroidfinal_task.domain.model.Joke
import com.fatalzero.rsandroidfinal_task.domain.usecase.JokeDeleteUseCase
import com.fatalzero.rsandroidfinal_task.domain.usecase.JokeFListUseCase
import com.fatalzero.rsandroidfinal_task.domain.usecase.JokeSendUseCase
import com.fatalzero.rsandroidfinal_task.domain.usecase.SearchUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavouriteViewModel @Inject constructor(var jokeFListUseCase: JokeFListUseCase)
    : ViewModel() {

    @Inject
    lateinit var JokeDeleteUseCase: JokeDeleteUseCase



    @Inject
    lateinit var jokeSearchUseCase: SearchUseCase

    @Inject
    lateinit var jokeSendUseCase: JokeSendUseCase

    var listDbLiveData = jokeFListUseCase()

    fun sendJoke(joke: Joke?) {
        jokeSendUseCase.execute(joke)
    }

    fun deleteJoke(joke: Joke?) {
        viewModelScope.launch {
            joke?.let { JokeDeleteUseCase(it) }
        }
    }



    fun searchJoke(query: String): LiveData<List<Joke>> {
        val searchQuery = "%$query%"
        return jokeSearchUseCase(searchQuery)
    }

}