package com.fatalzero.rsandroidfinal_task.domain.repository

import androidx.lifecycle.LiveData
import com.fatalzero.rsandroidfinal_task.domain.model.Filters
import com.fatalzero.rsandroidfinal_task.domain.model.Joke


interface JokesListRepository {
    suspend fun getJokesList(count: Int, range: String): List<Joke>
    suspend fun saveJoke(joke: Joke?)
    suspend fun deleteJoke(joke: Joke?)
    suspend fun getJoke(id: String): Joke
    fun getCategories(): LiveData<List<String>>
    fun getJokesListFromDB(): LiveData<List<Joke>>
    fun searchQuery(searchText: String): LiveData<List<Joke>>
    fun addFilter(filter: String)
    fun removeFilter(filter: String)
    fun clearFilters()
}
