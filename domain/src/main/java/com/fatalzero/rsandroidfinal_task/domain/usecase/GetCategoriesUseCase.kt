package com.fatalzero.rsandroidfinal_task.domain.usecase

import androidx.lifecycle.LiveData
import com.fatalzero.rsandroidfinal_task.domain.repository.JokesListRepository

class GetCategoriesUseCase(private var repository: JokesListRepository) {
     operator fun invoke(): LiveData<List<String>> {
        return repository.getCategories()
    }
}