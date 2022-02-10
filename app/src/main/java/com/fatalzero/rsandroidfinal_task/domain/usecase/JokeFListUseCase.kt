package com.fatalzero.rsandroidfinal_task.domain.usecase

import androidx.lifecycle.LiveData
import com.fatalzero.rsandroidfinal_task.domain.repository.JokesListRepository
import javax.inject.Inject
import com.fatalzero.rsandroidfinal_task.domain.model.Joke

class JokeFListUseCase @Inject constructor(var repository: JokesListRepository){
    suspend operator fun invoke(): LiveData<List<Joke>>{
        return repository.getJokesListFromDB()
    }
}