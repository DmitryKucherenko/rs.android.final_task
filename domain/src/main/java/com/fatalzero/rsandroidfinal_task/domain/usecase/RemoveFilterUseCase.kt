package com.fatalzero.rsandroidfinal_task.domain.usecase

import com.fatalzero.rsandroidfinal_task.domain.repository.JokesListRepository

class RemoveFilterUseCase(private var repository: JokesListRepository) {
    operator fun invoke(filter: String) {
        return repository.removeFilter(filter)
    }
}


