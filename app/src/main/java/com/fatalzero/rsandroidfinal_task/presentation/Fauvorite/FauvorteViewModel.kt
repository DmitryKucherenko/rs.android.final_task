package com.fatalzero.rsandroidfinal_task.presentation.Fauvorite

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fatalzero.rsandroidfinal_task.R
import com.fatalzero.rsandroidfinal_task.domain.model.Joke
import com.fatalzero.rsandroidfinal_task.domain.usecase.JokeDeleteUseCase
import com.fatalzero.rsandroidfinal_task.domain.usecase.JokeFListUseCase
import com.fatalzero.rsandroidfinal_task.domain.usecase.JokeSendUseCase
import com.fatalzero.rsandroidfinal_task.utils.DialogService
import kotlinx.coroutines.launch
import javax.inject.Inject

class FauvorteViewModel @Inject constructor(
    var context: Context,
    var jokeSendUseCase: JokeSendUseCase,
    var jokeFListUseCase: JokeFListUseCase,
    var JokeDeleteUseCase: JokeDeleteUseCase,
    var deleteDialogService: DialogService

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
        deleteDialogService.showDialog(context.resources.getString(R.string.Delete_Message)) {
            deleteJoke(
                joke
            )
        }
    }

}