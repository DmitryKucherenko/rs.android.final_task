package com.fatalzero.rsandroidfinal_task.presentation.Fauvorite.adapter

import com.fatalzero.rsandroidfinal_task.domain.model.Joke

interface FauvItemClickListener {
    fun onItemClick(joke: com.fatalzero.rsandroidfinal_task.domain.model.Joke?)
    fun onSaveItemClick(joke: com.fatalzero.rsandroidfinal_task.domain.model.Joke?)
    fun onEditItemClick(id:String)
    fun onDeleteItemClick(joke: com.fatalzero.rsandroidfinal_task.domain.model.Joke?)
}