package com.fatalzero.rsandroidfinal_task.presentation.Fauvorite.adapter

import androidx.recyclerview.widget.DiffUtil

import com.fatalzero.rsandroidfinal_task.domain.model.Joke

object DiffCallback : DiffUtil.ItemCallback<com.fatalzero.rsandroidfinal_task.domain.model.Joke>() {
    override fun areItemsTheSame(oldItem: com.fatalzero.rsandroidfinal_task.domain.model.Joke, newItem: com.fatalzero.rsandroidfinal_task.domain.model.Joke): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: com.fatalzero.rsandroidfinal_task.domain.model.Joke, newItem: com.fatalzero.rsandroidfinal_task.domain.model.Joke): Boolean {
        return oldItem == newItem
    }
}
