package com.fatalzero.rsandroidfinal_task.presentation.Fauvorite.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.fatalzero.rsandroidfinal_task.databinding.FauvoriteJokeItemBinding
import com.fatalzero.rsandroidfinal_task.domain.model.Joke
import com.fatalzero.rsandroidfinal_task.presentation.JokeList.adapter.ItemClickListener

class FJokeHolder (
    private val itemClickListener: ItemClickListener?,
    binding: FauvoriteJokeItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private val jokeText = binding.jokeText
        private val category = binding.categoryText
        private val share = binding.shareButton
        private val delete = binding.deleteButton
        fun bind(joke: Joke?) {
            jokeText.text = joke?.joke
            category.text = "Category: ${joke?.category}"
            share.setOnClickListener {
                itemClickListener?.onItemClick(joke)
            }
            delete.setOnClickListener {
                itemClickListener?.onDeleteItemClick(joke)
            }
        }
}