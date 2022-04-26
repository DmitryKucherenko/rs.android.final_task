package com.fatalzero.rsandroidfinal_task.domain.repository

import androidx.lifecycle.LiveData
import com.fatalzero.rsandroidfinal_task.domain.model.Filters
import com.fatalzero.rsandroidfinal_task.domain.model.Joke



interface JokesListRepository {
    suspend fun getJokesList(count: Int, range: String): List<Joke>
    suspend fun saveJoke(joke: Joke?)
    suspend fun deleteJoke(joke: Joke?)
    suspend fun getJoke(id:String): Joke
    fun getJokesListFromDB(): LiveData<List<Joke>>
    fun searchQuery(query:String):LiveData<List<Joke>>
    fun addFilter(filter: Filters)
     fun removeFilter(filter: Filters)
     fun clearFilters()
}
