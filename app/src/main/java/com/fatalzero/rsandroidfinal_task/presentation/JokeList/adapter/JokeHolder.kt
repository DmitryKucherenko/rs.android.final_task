package com.fatalzero.rsandroidfinal_task.presentation.JokeList.adapter

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
    private val save = binding.saveButton
    fun bind(joke: com.fatalzero.rsandroidfinal_task.domain.model.Joke?) {
        jokeText.text = joke?.joke
        category.text = "Category: ${joke?.category}"
        share.setOnClickListener {
            itemClickListener?.onItemClick(joke)
        }
        save.setOnClickListener {
            itemClickListener?.onSaveItemClick(joke)
        }
    }
}
