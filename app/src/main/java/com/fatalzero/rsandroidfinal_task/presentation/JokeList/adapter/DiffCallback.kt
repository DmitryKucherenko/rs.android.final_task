package com.fatalzero.rsandroidfinal_task.presentation.JokeList.adapter

import androidx.recyclerview.widget.DiffUtil

import com.fatalzero.rsandroidfinal_task.domain.model.Joke

object DiffCallback : DiffUtil.ItemCallback<Joke>() {
    override fun areItemsTheSame(
        oldItem: Joke,
        newItem: Joke
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Joke,
        newItem: Joke
    ): Boolean {
        return oldItem == newItem
    }
}
