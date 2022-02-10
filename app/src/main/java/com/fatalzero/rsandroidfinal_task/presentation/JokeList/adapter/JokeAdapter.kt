package com.fatalzero.rsandroidfinal_task.presentation.JokeList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.fatalzero.rsandroidfinal_task.databinding.JokeItemBinding
import com.fatalzero.rsandroidfinal_task.domain.model.Joke

class JokeAdapter(private val itemClickListener: ItemClickListener?) :
    PagingDataAdapter<Joke, JokeHolder>(DiffCallback) {
    override fun onBindViewHolder(holder: JokeHolder, position: Int) {
        val joke = getItem(position)
        holder.bind(joke)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = JokeItemBinding.inflate(layoutInflater, parent, false)
        return JokeHolder(itemClickListener, binding, parent.context)
    }

}
