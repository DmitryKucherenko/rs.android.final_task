package com.fatalzero.rsandroidfinal_task.presentation.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.fatalzero.rsandroidfinal_task.databinding.JokeItemBinding
import com.fatalzero.rsandroidfinal_task.domain.model.Joke


class JokeHolder(
    private val itemClickListener: ItemClickListener?,
    binding: JokeItemBinding,
    var context: Context
) : RecyclerView.ViewHolder(binding.root) {
    private val jokeText = binding.jokeText
    private val category = binding.categoryText
    private val share = binding.shareButton
    fun bind(joke: Joke?) {
        jokeText.text = joke?.joke
        category.text = "Category: ${joke?.category}"
        share.setOnClickListener {
            itemClickListener?.onItemClick(joke)
        }
    }
}
