package com.fatalzero.rsandroidfinal_task.domain.usecase

import androidx.lifecycle.LiveData
import com.fatalzero.rsandroidfinal_task.domain.model.Filters
import com.fatalzero.rsandroidfinal_task.domain.model.Joke
import com.fatalzero.rsandroidfinal_task.domain.repository.JokesListRepository

class RemoveFilterUseCase(private var repository: JokesListRepository) {
    operator fun invoke(filter: Filters) {
        return repository.removeFilter(filter)
    }
}


