package com.fatalzero.rsandroidfinal_task.data.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.sqlite.db.SimpleSQLiteQuery
import com.fatalzero.rsandroidfinal_task.data.database.AppDatabase
import com.fatalzero.rsandroidfinal_task.data.mapper.JokeMapper
import com.fatalzero.rsandroidfinal_task.data.network.ApiService
import com.fatalzero.rsandroidfinal_task.domain.model.Joke
import com.fatalzero.rsandroidfinal_task.domain.repository.JokesListRepository
import com.fatalzero.rsandroidfinal_task.utils.ShowMessage

private const val QUERY_PATTERN =
    "SELECT * FROM jokedbmodel WHERE (%s joke like \"%s\") COLLATE NOCASE"
private const val FILTER_CONDITIONS = "category IN (%s) AND"
private const val SEPARATOR = "\",\""
private const val PREFIX_POSTFIX = "\""

class JokesListRepositoryImpl(
    private var jokesApiService: ApiService,
    context: Context,
    private var showMessage: ShowMessage
) :
    JokesListRepository {
    private var jokeDao = AppDatabase.getInstance(context).jokeDao()
    private val filters = mutableSetOf<String>()

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

    override fun getCategories(): LiveData<List<String>> {
        try {
            return jokeDao.getCategories()
        } catch (e: Exception) {
            showMessage(e.toString())
            throw e
        }
    }

    private fun buildQuery(searchText: String): SimpleSQLiteQuery {

        val filterQuery = if (filters.isNotEmpty()) String.format(
            FILTER_CONDITIONS,
            filters.joinToString(SEPARATOR, prefix =PREFIX_POSTFIX, postfix = PREFIX_POSTFIX)
        ) else ""

        return SimpleSQLiteQuery(String.format(QUERY_PATTERN, filterQuery, searchText))
    }

    override fun searchQuery(searchText: String): LiveData<List<Joke>> {
        try {
            return Transformations.map(
                jokeDao.search(buildQuery(searchText))
            ) {
                it.map {
                    JokeMapper.jokeDbModelToJoke(it)
                }
            }
        } catch (e: Exception) {
            showMessage(e.toString())
            throw e
        }
    }


    override fun addFilter(filter: String) {
        filters.add(filter)

    }


    override fun removeFilter(filter: String) {
        filters.remove(filter)
    }

    override fun clearFilters() {
        filters.clear()
    }


}
