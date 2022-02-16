package com.fatalzero.rsandroidfinal_task.domain.usecase

import com.fatalzero.rsandroidfinal_task.domain.model.Joke
import javax.inject.Inject


interface SendJoke {
    fun sendJoke(joke: Joke?)
}

class JokeSendUseCase @Inject constructor(var sendJokeService: SendJoke) {
    fun execute(joke: Joke?) {
        sendJokeService.sendJoke(joke)
    }
}
