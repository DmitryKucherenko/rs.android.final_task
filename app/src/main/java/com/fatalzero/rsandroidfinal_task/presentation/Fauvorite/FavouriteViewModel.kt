package com.fatalzero.rsandroidfinal_task.presentation.Fauvorite

import androidx.lifecycle.*
import com.fatalzero.rsandroidfinal_task.domain.model.Joke
import com.fatalzero.rsandroidfinal_task.domain.usecase.*
import com.fatalzero.rsandroidfinal_task.utils.Constants.ANY
import com.fatalzero.rsandroidfinal_task.utils.Constants.DEF_SEARCH_QUERY
import com.fatalzero.rsandroidfinal_task.utils.dialog.DialogService
import kotlinx.coroutines.launch

class FavouriteViewModel(
    private var JokeDeleteUseCase: JokeDeleteUseCase,
    private var jokeSearchUseCase: SearchUseCase,
    private var jokeSendUseCase: JokeSendUseCase,
    private var dialogService: DialogService,
    private var addFilterUseCase: AddFilterUseCase,
    private var removeFilterUseCase: RemoveFilterUseCase,
    private var clearFilterUseCase: ClearFilterUseCase,
    private var getCategoriesUseCase: GetCategoriesUseCase
    ) : ViewModel() {

    private var _checkedFilters = MutableLiveData(mutableListOf(ANY))
    val checkedFilters: LiveData<MutableList<String>> get() = _checkedFilters
    private var searchQuery = MutableLiveData(DEF_SEARCH_QUERY)

    var listDbLiveData = Transformations.switchMap(DoubleTrigger(_checkedFilters, searchQuery)) {
        jokeSearchUseCase(searchQuery.value ?: "")
    }

    fun getCategory():LiveData<List<String>>{
        return getCategoriesUseCase()
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


    fun addFilter(filter: String) {
        val filters = checkedFilters.value
        filters?.apply {
            remove(ANY)
            add(filter)
        }
        addFilterUseCase(filter)
        _checkedFilters.value = filters
    }

    fun removeFilter(filter: String) {
        removeFilterUseCase(filter)
        val filters = checkedFilters.value

        filters?.apply {
            if (size == 1) _checkedFilters.value = mutableListOf(ANY)
            else {
                remove(filter)
                _checkedFilters.value = this
            }
        }
    }

    fun clearFilter() {
        clearFilterUseCase()
       _checkedFilters.value = mutableListOf(ANY)
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

