package com.fatalzero.rsandroidfinal_task.domain.usecase

import com.fatalzero.rsandroidfinal_task.domain.model.Filters
import com.fatalzero.rsandroidfinal_task.domain.repository.JokesListRepository

class AddFilterUseCase(private var repository: JokesListRepository) {
    operator fun invoke(filter: Filters) {
        return repository.addFilter(filter)
    }
}


