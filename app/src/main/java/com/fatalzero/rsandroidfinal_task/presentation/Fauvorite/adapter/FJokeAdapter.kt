package com.fatalzero.rsandroidfinal_task.presentation.Fauvorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.fatalzero.rsandroidfinal_task.databinding.FauvoriteJokeItemBinding
import com.fatalzero.rsandroidfinal_task.domain.model.Joke

class FJokeAdapter(private val itemClickListener: FauvItemClickListener?) :
    ListAdapter<Joke, FJokeHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FJokeHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FauvoriteJokeItemBinding.inflate(layoutInflater, parent, false)
        return FJokeHolder(itemClickListener, binding)
    }

    override fun onBindViewHolder(holder: FJokeHolder, position: Int) {
        val joke = currentList[position]
        holder.bind(joke)
    }

    override fun getItemCount() = currentList.size
}