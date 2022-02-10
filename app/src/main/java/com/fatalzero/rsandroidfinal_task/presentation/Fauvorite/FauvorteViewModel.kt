package com.fatalzero.rsandroidfinal_task.presentation.Fauvorite

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fatalzero.rsandroidfinal_task.data.repository.JokesListRepositoryImpl
import com.fatalzero.rsandroidfinal_task.domain.model.Joke
import com.fatalzero.rsandroidfinal_task.domain.usecase.JokeSendUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class FauvorteViewModel @Inject constructor(
    var jokeSendUseCase: JokeSendUseCase,var repository:JokesListRepositoryImpl
) : ViewModel() {

    init{
        Log.d("FauvoriteListFragment","View model create")
    }

    fun sendJoke(joke: Joke?) {
        jokeSendUseCase.execute(joke)
    }

    fun deleteJoke(joke: Joke?) {
        viewModelScope.launch {
            repository.deleteJoke(joke)
        }
    }

  fun getDBlist():LiveData<List<Joke>>{
          return  repository.getJokesListFromDB()
  }

}