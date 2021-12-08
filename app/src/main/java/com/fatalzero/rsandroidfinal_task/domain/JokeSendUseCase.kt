package com.fatalzero.rsandroidfinal_task.domain

import com.fatalzero.rsandroidfinal_task.domain.model.Joke
import javax.inject.Inject

class JokeSendUseCase @Inject constructor(var sendJokeService: SendJoke) {
    fun execute(joke: Joke?) {
        sendJokeService.sendJoke(joke)
    }
}
