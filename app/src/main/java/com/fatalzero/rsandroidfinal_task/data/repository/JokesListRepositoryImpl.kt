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
import javax.inject.Inject

class JokesListRepositoryImpl @Inject constructor(var jokesApiService: ApiService,context: Context) :
    JokesListRepository {



    private var jokeDao = AppDatabase.getInstance(context).jokeDao()

    override suspend fun getJokesList(count: Int, range: String): List<Joke> {
        return JokeMapper.mapJsonContainerToListJoke(
            jokesApiService.getResponse(
                count,
                range
            ).jokes ?: emptyList()
        )
    }

    override suspend fun saveJoke(joke: Joke?) {
        jokeDao.insertJoke(JokeMapper.jokeToJokeDbModel(joke))
    }

    override suspend fun deleteJoke(joke: Joke?) {
        jokeDao.deleteJoke(JokeMapper.jokeToJokeDbModel(joke))
    }

    override  fun getJokesListFromDB(): LiveData<List<Joke>> {
        return  Transformations.map(jokeDao.getJokes()){
            it.map{
                JokeMapper.jokeDbModelToJoke(it)
            }
        }
    }



}
