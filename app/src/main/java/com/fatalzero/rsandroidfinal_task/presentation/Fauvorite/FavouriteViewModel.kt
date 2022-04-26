package com.fatalzero.rsandroidfinal_task.presentation.Fauvorite

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fatalzero.rsandroidfinal_task.domain.model.Filters
import com.fatalzero.rsandroidfinal_task.domain.model.Joke
import com.fatalzero.rsandroidfinal_task.domain.usecase.*
import com.fatalzero.rsandroidfinal_task.utils.dialog.DialogService
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavouriteViewModel(
    jokeFListUseCase: JokeFListUseCase,
    private var JokeDeleteUseCase: JokeDeleteUseCase,
    private var jokeSearchUseCase: SearchUseCase,
    private var jokeSendUseCase: JokeSendUseCase,
    private var dialogService: DialogService,
    private var addFilterUseCase: AddFilterUseCase,
    private var removeFilterUseCase: RemoveFilterUseCase,
    private var clearFilterUseCase: ClearFilterUseCase
) : ViewModel() {
    private var _checkedFilters = MutableLiveData(mutableListOf(Filters.Any))
    val checkedFilters: LiveData<MutableList<Filters>> get() = requireNotNull(_checkedFilters)

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


    fun addFilter(filter: Filters) {
        Log.d("REPO", "addFilter")
        val filters = checkedFilters.value
        filters?.apply {
            remove(Filters.Any)
            add(filter)
        }
        _checkedFilters.value = filters
        addFilterUseCase(filter)

    }

    fun removeFilter(filter: Filters) {

        Log.d("REPO", "addFilter")
        val filters = checkedFilters.value
        filters?.apply {
            if (size == 1) _checkedFilters.value = mutableListOf(Filters.Any)
            else {
                remove(filter)
                _checkedFilters.value = this
            }
        }
        removeFilterUseCase(filter)
    }

    fun clearFilter(){
        _checkedFilters.value = mutableListOf(Filters.Any)
        clearFilterUseCase()



    }

}