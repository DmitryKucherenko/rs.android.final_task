package com.fatalzero.rsandroidfinal_task.presentation.Fauvorite.adapter

import com.fatalzero.rsandroidfinal_task.domain.model.Joke


interface FauvItemClickListener {
    fun onItemClick(joke: Joke?)
    fun onSaveItemClick(joke: Joke?)
    fun onEditItemClick(id: String)
    fun onDeleteItemClick(joke: Joke?)
}