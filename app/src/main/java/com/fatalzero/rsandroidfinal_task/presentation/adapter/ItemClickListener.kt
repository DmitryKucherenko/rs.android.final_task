package com.fatalzero.rsandroidfinal_task.presentation.adapter

import com.fatalzero.rsandroidfinal_task.domain.model.Joke

interface ItemClickListener {
    fun onItemClick(joke: Joke?)
}
