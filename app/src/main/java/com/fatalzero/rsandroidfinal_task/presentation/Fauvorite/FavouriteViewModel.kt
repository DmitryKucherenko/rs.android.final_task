package com.fatalzero.rsandroidfinal_task.presentation.Fauvorite

import android.util.Log
import androidx.lifecycle.*
import com.fatalzero.rsandroidfinal_task.domain.model.Filters
import com.fatalzero.rsandroidfinal_task.domain.model.Joke
import com.fatalzero.rsandroidfinal_task.domain.usecase.*
import com.fatalzero.rsandroidfinal_task.utils.dialog.DialogService
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavouriteViewModel(
    private var JokeDeleteUseCase: JokeDeleteUseCase,
    private var jokeSearchUseCase: SearchUseCase,
    private var jokeSendUseCase: JokeSendUseCase,
    private var dialogService: DialogService,
    private var addFilterUseCase: AddFilterUseCase,
    private var removeFilterUseCase: RemoveFilterUseCase,
    private var clearFilterUseCase: ClearFilterUseCase
) : ViewModel() {

    private var _checkedFilters = MutableLiveData(mutableListOf(Filters.Any))
    val checkedFilters: LiveData<MutableList<Filters>> get() = _checkedFilters
    private var searchQuery = MutableLiveData("%%")


    var listDbLiveData = Transformations.switchMap(DoubleTrigger(_checkedFilters, searchQuery)) {
        Log.d("REPO", "TRANSFORMATION")
        jokeSearchUseCase(searchQuery.value ?: "")
    }


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

    fun searchJoke(query: String) {
        searchQuery.value = "%$query%"
    }


    fun addFilter(filter: Filters) {
        Log.d("REPO", "addFilter $filter")
        val filters = checkedFilters.value
        filters?.apply {
            remove(Filters.Any)
            add(filter)
        }
        addFilterUseCase(filter)
        _checkedFilters.value = filters
    }

    fun removeFilter(filter: Filters) {
        Log.d("REPO", "remove filter: $filter")
        removeFilterUseCase(filter)
        val filters = checkedFilters.value

        filters?.apply {
            if (size == 1) _checkedFilters.value = mutableListOf(Filters.Any)
            else {
                remove(filter)
                _checkedFilters.value = this
            }
        }
    }

    fun clearFilter() {
        Log.d("REPO", "clear filter")
        clearFilterUseCase()
       _checkedFilters.value = mutableListOf(Filters.Any)
    }
}

class DoubleTrigger<A, B>(a: LiveData<A>, b: LiveData<B>) : MediatorLiveData<Pair<A?, B?>>() {
    private var isAEmited = false
    private var isBEmited = false
    init {
        addSource(a) {
            isAEmited = true
            if (isAEmited && isBEmited)
                value = it to b.value
        }
        addSource(b) {
            isBEmited = true
            if (isAEmited && isBEmited)
                value = a.value to it
        }
    }
}

