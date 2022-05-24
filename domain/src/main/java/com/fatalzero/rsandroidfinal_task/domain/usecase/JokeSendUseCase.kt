package com.fatalzero.rsandroidfinal_task.domain.usecase

import com.fatalzero.rsandroidfinal_task.domain.model.Joke


interface SendJoke {
    fun sendJoke(joke: Joke?)
}

class JokeSendUseCase (private var sendJokeService: SendJoke) {
    fun execute(joke: Joke?) {
        sendJokeService.sendJoke(joke)
    }
}
