package com.fatalzero.rsandroidfinal_task.domain.repository

import androidx.lifecycle.LiveData
import com.fatalzero.rsandroidfinal_task.domain.model.Joke


interface JokesListRepository {
    suspend fun getJokesList(count: Int, range: String): List<Joke>
    suspend fun saveJoke(joke: Joke?)
    suspend fun deleteJoke(joke: Joke?)
    fun getJokesListFromDB(): LiveData<List<Joke>>
}
