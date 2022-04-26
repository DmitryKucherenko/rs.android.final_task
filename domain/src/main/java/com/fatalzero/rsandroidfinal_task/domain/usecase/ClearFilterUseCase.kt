package com.fatalzero.rsandroidfinal_task.domain.usecase

import com.fatalzero.rsandroidfinal_task.domain.repository.JokesListRepository

class ClearFilterUseCase(private var repository: JokesListRepository) {
    operator fun invoke() {
        return repository.clearFilters()
    }
}
