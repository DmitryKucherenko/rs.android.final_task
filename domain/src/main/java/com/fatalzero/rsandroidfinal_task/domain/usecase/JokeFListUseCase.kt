package com.fatalzero.rsandroidfinal_task.domain.usecase

import androidx.lifecycle.LiveData
import com.fatalzero.rsandroidfinal_task.domain.repository.JokesListRepository
import com.fatalzero.rsandroidfinal_task.domain.model.Joke

class JokeFListUseCase(private var repository: JokesListRepository) {
     operator fun invoke(): LiveData<List<Joke>> {
        return repository.getJokesListFromDB()
    }
}