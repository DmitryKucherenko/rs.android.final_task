package com.fatalzero.rsandroidfinal_task.data.repository

import com.fatalzero.rsandroidfinal_task.data.mapper.JokeMapper
import com.fatalzero.rsandroidfinal_task.data.network.ApiService
import com.fatalzero.rsandroidfinal_task.domain.model.Joke
import com.fatalzero.rsandroidfinal_task.domain.repository.JokesListRepository
import javax.inject.Inject

class JokesListRepositoryImpl @Inject constructor(var jokesApiService: ApiService) :
    JokesListRepository {

    override suspend fun getJokesList(count: Int, range: String): List<Joke> {
        return JokeMapper().mapJsonContainerToListJoke(
            jokesApiService.getResponse(
                count,
                range
            ).jokes ?: emptyList()
        )
    }
}
