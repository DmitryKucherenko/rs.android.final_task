package com.fatalzero.rsandroidfinal_task.domain.usecase

import androidx.lifecycle.LiveData
import com.fatalzero.rsandroidfinal_task.domain.model.Joke
import com.fatalzero.rsandroidfinal_task.domain.repository.JokesListRepository
import javax.inject.Inject

class SearchUseCase @Inject constructor(var repository: JokesListRepository) {
    operator fun invoke(searchQuery:String): LiveData<List<Joke>> {
        return repository.searchQuery(searchQuery)
    }
}


