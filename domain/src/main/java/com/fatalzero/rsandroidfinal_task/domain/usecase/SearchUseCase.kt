package com.fatalzero.rsandroidfinal_task.domain.usecase

import androidx.lifecycle.LiveData
import com.fatalzero.rsandroidfinal_task.domain.model.Joke
import com.fatalzero.rsandroidfinal_task.domain.repository.JokesListRepository


class SearchUseCase (private var repository: JokesListRepository) {
    operator fun invoke(searchQuery:String): LiveData<List<Joke>> {
        return repository.searchQuery(searchQuery)
    }
}


