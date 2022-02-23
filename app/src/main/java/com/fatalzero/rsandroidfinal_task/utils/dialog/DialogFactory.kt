package com.fatalzero.rsandroidfinal_task.utils.dialog

import android.app.AlertDialog
import android.content.Context
import javax.inject.Inject

class DialogFactory @Inject constructor(var context: Context) {
    fun getDialog(): AlertDialog.Builder? = (context as? CurrentActivity)?.let {
        AlertDialog.Builder(it.getCurrentActivity())
            .setNegativeButton(android.R.string.cancel) { _, _ ->
            }
            .setPositiveButton(android.R.string.ok) { _, _ ->
            }
    }

}