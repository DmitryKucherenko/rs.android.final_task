package com.fatalzero.rsandroidfinal_task.domain.repository

import com.fatalzero.rsandroidfinal_task.domain.model.Joke


interface JokesListRepository {
    suspend fun getJokesList(count: Int, range: String): List<Joke>
}
