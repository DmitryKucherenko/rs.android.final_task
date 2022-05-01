package com.fatalzero.rsandroidfinal_task.data.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.fatalzero.rsandroidfinal_task.data.database.AppDatabase
import com.fatalzero.rsandroidfinal_task.data.mapper.JokeMapper
import com.fatalzero.rsandroidfinal_task.data.network.ApiService
import com.fatalzero.rsandroidfinal_task.domain.model.Filters
import com.fatalzero.rsandroidfinal_task.domain.model.Joke
import com.fatalzero.rsandroidfinal_task.domain.repository.JokesListRepository
import com.fatalzero.rsandroidfinal_task.utils.ShowMessage

class JokesListRepositoryImpl(
    private var jokesApiService: ApiService,
    context: Context,
    private var showMessage: ShowMessage
) :
    JokesListRepository {
    private var jokeDao = AppDatabase.getInstance(context).jokeDao()
    private val filters = mutableSetOf<Filters>()
    override suspend fun getJokesList(count: Int, range: String): List<Joke> {
        try {
            return JokeMapper.mapJsonContainerToListJoke(
                jokesApiService.getResponse(
                    count,
                    range
                ).jokes ?: emptyList()
            )
        } catch (e: Exception) {
            showMessage(e.toString())
            Log.d("ERROR", e.toString())
            throw e
        }

    }

    override suspend fun saveJoke(joke: Joke?) {
        try {
            jokeDao.insertJoke(JokeMapper.jokeToJokeDbModel(joke))
        } catch (e: Exception) {
            Log.d("ERROR", e.toString())
            showMessage(e.toString())
            throw e
        }
    }

    override suspend fun deleteJoke(joke: Joke?) {
        try {
            jokeDao.deleteJoke(JokeMapper.jokeToJokeDbModel(joke))
        } catch (e: Exception) {
            showMessage(e.toString())
            throw e
        }
    }

    override suspend fun getJoke(id: String): Joke {
        try {
            return JokeMapper.jokeDbModelToJoke(jokeDao.getJoke(id))
        } catch (e: Exception) {
            showMessage(e.toString())
            throw e
        }
    }

    override fun getJokesListFromDB(): LiveData<List<Joke>> {
        try {
            return Transformations.map(jokeDao.getJokes()) {
                it.map {
                    JokeMapper.jokeDbModelToJoke(it)
                }
            }
        } catch (e: Exception) {
            showMessage(e.toString())
            throw e
        }
    }

    override fun searchQuery(query: String): LiveData<List<Joke>> {
        try {
            Log.d("REPO","FROM REPOSITORY set FILTERS IS $filters")
            return Transformations.map(jokeDao.search(query,if(filters.isEmpty())Filters.values().map{it.toString()} else filters.map { it.toString()})){
                it.map{
                    JokeMapper.jokeDbModelToJoke(it)
                }
            }
        } catch (e: Exception) {
            showMessage(e.toString())
            throw e
        }
    }


    override fun addFilter(filter: Filters) {
        filters.add(filter)

    }



    override fun removeFilter(filter: Filters) {
        filters.remove(filter)
    }

    override fun clearFilters() {
        filters.clear()
    }


}
