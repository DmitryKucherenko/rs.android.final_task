package com.fatalzero.rsandroidfinal_task.domain.usecase;

import com.fatalzero.rsandroidfinal_task.domain.model.Joke
import com.fatalzero.rsandroidfinal_task.domain.repository.JokesListRepository;

import javax.inject.Inject;

class JokeDeleteUseCase @Inject constructor(var repository: JokesListRepository) {
    suspend operator fun invoke(joke: Joke) {
        repository?.deleteJoke(joke)
    }
}
