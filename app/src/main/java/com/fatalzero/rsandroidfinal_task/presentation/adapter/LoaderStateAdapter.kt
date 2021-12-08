package com.fatalzero.rsandroidfinal_task.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.fatalzero.rsandroidfinal_task.databinding.ItemJokeLoaderBinding

class LoaderStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<LoaderViewHolder>() {

    override fun onBindViewHolder(holder: LoaderViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoaderViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemJokeLoaderBinding.inflate(layoutInflater, parent, false)
        return LoaderViewHolder(binding, retry)
    }
}
