package com.fatalzero.rsandroidfinal_task.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider



class ViewModelFactory (
    private val viewModels: @JvmSuppressWildcards Map<String, ViewModel>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModels[modelClass.simpleName] as T
    }
}

