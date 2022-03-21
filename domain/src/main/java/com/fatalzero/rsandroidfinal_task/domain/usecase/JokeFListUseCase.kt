package com.fatalzero.rsandroidfinal_task.domain.usecase

import androidx.lifecycle.LiveData
import com.fatalzero.rsandroidfinal_task.domain.repository.JokesListRepository
import javax.inject.Inject
import com.fatalzero.rsandroidfinal_task.domain.model.Joke

class JokeFListUseCase (var repository: JokesListRepository) {
     operator fun invoke(): LiveData<List<Joke>> {
        return repository.getJokesListFromDB()
    }
}