package com.fatalzero.rsandroidfinal_task.presentation.Fauvorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fatalzero.rsandroidfinal_task.domain.model.Joke
import com.fatalzero.rsandroidfinal_task.domain.usecase.JokeGetUseCase
import com.fatalzero.rsandroidfinal_task.domain.usecase.JokeSaveUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddViewModel @Inject constructor(
    var saveUseCase: com.fatalzero.rsandroidfinal_task.domain.usecase.JokeSaveUseCase,
    var jokeGetUseCase: com.fatalzero.rsandroidfinal_task.domain.usecase.JokeGetUseCase
) : ViewModel() {

    private var _finish = MutableLiveData<Boolean>(false)
    val finish: LiveData<Boolean>
        get() = _finish

    private var _joke = MutableLiveData<com.fatalzero.rsandroidfinal_task.domain.model.Joke>(com.fatalzero.rsandroidfinal_task.domain.model.Joke())
    val joke: LiveData<com.fatalzero.rsandroidfinal_task.domain.model.Joke>
        get() = _joke

    fun save(joke: com.fatalzero.rsandroidfinal_task.domain.model.Joke?) {
        viewModelScope.launch {
            saveUseCase(joke)
            _finish.postValue(true)
        }
    }

    fun get(id: String) {
        viewModelScope.launch {
            jokeGetUseCase(id)?.let {
                _joke.postValue(it)
            }

        }
    }
}