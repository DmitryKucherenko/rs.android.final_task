package com.fatalzero.rsandroidfinal_task.domain.usecase

import com.fatalzero.rsandroidfinal_task.domain.model.Joke
import com.fatalzero.rsandroidfinal_task.domain.repository.JokesListRepository
import javax.inject.Inject

interface IJokesListUseCase {
    suspend fun execute(count: Int, range: String): List<Joke>
}

class JokesListUseCase (var repository: JokesListRepository) :
    IJokesListUseCase {
    override suspend fun execute(count: Int, range: String): List<Joke> {
        return repository.getJokesList(count, range)
    }
}
