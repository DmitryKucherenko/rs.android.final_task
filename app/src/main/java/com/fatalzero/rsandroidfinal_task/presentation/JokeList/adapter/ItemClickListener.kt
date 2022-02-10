package com.fatalzero.rsandroidfinal_task.presentation.JokeList.adapter

import com.fatalzero.rsandroidfinal_task.domain.model.Joke

interface ItemClickListener {
    fun onItemClick(joke: Joke?)
    fun onSaveItemClick(joke: Joke?)
    fun onDeleteItemClick(joke: Joke?)
}
