package com.fatalzero.rsandroidfinal_task.domain

import com.fatalzero.rsandroidfinal_task.domain.model.Joke

interface SendJoke {
    fun sendJoke(joke: Joke?)
}
