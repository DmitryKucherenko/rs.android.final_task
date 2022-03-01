package com.fatalzero.rsandroidfinal_task.presentation.Fauvorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fatalzero.rsandroidfinal_task.domain.model.Joke
import com.fatalzero.rsandroidfinal_task.domain.usecase.JokeDeleteUseCase
import com.fatalzero.rsandroidfinal_task.domain.usecase.JokeFListUseCase
import com.fatalzero.rsandroidfinal_task.domain.usecase.JokeSendUseCase
import com.fatalzero.rsandroidfinal_task.utils.DialogFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

class FauvorteViewModel @Inject constructor(
    var jokeSendUseCase: JokeSendUseCase,
    var jokeFListUseCase: JokeFListUseCase,
    var JokeDeleteUseCase: JokeDeleteUseCase,
    var dialogFactory: DialogFactory
) : ViewModel() {

    var listDbLiveData = jokeFListUseCase()

    fun sendJoke(joke: Joke?) {
        jokeSendUseCase.execute(joke)
    }

    fun deleteJoke(joke: Joke?) {
        viewModelScope.launch {
            joke?.let { JokeDeleteUseCase(it) }
        }
    }

    fun showDeleteDialog(joke: Joke?) {
        dialogFactory.getDeleteDialog {
            deleteJoke(
                joke
            )
        }?.show()
    }

}