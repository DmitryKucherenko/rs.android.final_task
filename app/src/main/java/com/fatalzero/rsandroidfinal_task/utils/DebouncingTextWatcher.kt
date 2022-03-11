package com.fatalzero.rsandroidfinal_task.utils


import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DebouncingTextWatcher(){
    companion object{
        fun getWatcher(coroutineScope: LifecycleCoroutineScope, actionTextChange: (sequence: CharSequence?) -> Unit) =
            object : TextWatcher {
                var debouncePeriod: Long = 500
                private var searchJob: Job? = null

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(
                    sequence: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                    searchJob?.cancel()
                    searchJob = coroutineScope.launch {
                        sequence?.let {
                            delay(debouncePeriod)
                            actionTextChange(sequence)
                        }
                    }

                }
                override fun afterTextChanged(s: Editable?) {}
            }
    }

}
