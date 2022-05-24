package com.fatalzero.rsandroidfinal_task.domain.usecase

import com.fatalzero.rsandroidfinal_task.domain.repository.JokesListRepository

class AddFilterUseCase(private var repository: JokesListRepository) {
    operator fun invoke(filter: String) {
        return repository.addFilter(filter)
    }
}


