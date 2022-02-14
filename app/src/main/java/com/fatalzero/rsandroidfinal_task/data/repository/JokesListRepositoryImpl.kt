package com.fatalzero.rsandroidfinal_task.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.map
import androidx.room.Room
import com.fatalzero.rsandroidfinal_task.data.database.AppDatabase
import com.fatalzero.rsandroidfinal_task.data.database.JokeDao
import com.fatalzero.rsandroidfinal_task.data.database.JokeDbModel
import com.fatalzero.rsandroidfinal_task.data.mapper.JokeMapper
import com.fatalzero.rsandroidfinal_task.data.network.ApiService
import com.fatalzero.rsandroidfinal_task.domain.model.Joke
import com.fatalzero.rsandroidfinal_task.domain.repository.JokesListRepository
import com.fatalzero.rsandroidfinal_task.utils.ShowMessage
import java.io.IOException
import javax.inject.Inject

class JokesListRepositoryImpl @Inject constructor(
    var jokesApiService: ApiService,
    context: Context
) :
    JokesListRepository {

    @Inject
    lateinit var showMessage: ShowMessage

    private var jokeDao = AppDatabase.getInstance(context).jokeDao()

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
            throw e
        }

    }

    override suspend fun saveJoke(joke: Joke?) {
        try {
            jokeDao.insertJoke(JokeMapper.jokeToJokeDbModel(joke))
        } catch (e: Exception) {
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


}
