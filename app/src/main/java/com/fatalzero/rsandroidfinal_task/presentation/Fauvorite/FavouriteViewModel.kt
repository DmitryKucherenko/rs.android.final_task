package com.fatalzero.rsandroidfinal_task.presentation.Fauvorite

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fatalzero.rsandroidfinal_task.domain.model.Filters
import com.fatalzero.rsandroidfinal_task.domain.model.Joke
import com.fatalzero.rsandroidfinal_task.domain.usecase.*
import com.fatalzero.rsandroidfinal_task.utils.dialog.DialogService
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavouriteViewModel (
    private var jokeFListUseCase: JokeFListUseCase,
    private var JokeDeleteUseCase: JokeDeleteUseCase,
    private var jokeSearchUseCase: SearchUseCase,
    private var jokeSendUseCase: JokeSendUseCase,
    private var dialogService: DialogService,
    private var addFilterUseCase: AddFilterUseCase,
    private var removeFilterUseCase: RemoveFilterUseCase
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
        dialogService.showDeleteDialog {
            deleteJoke(joke)
        }
    }

    fun searchJoke(query: String): LiveData<List<Joke>> {
        val searchQuery = "%$query%"
        return jokeSearchUseCase(searchQuery)
    }


    fun addFilter(filter: Filters){
        Log.d("REPO","addFilter")
        addFilterUseCase(filter)
    }

    fun removeFilter(filter: Filters) {
        removeFilterUseCase(filter)
    }

}