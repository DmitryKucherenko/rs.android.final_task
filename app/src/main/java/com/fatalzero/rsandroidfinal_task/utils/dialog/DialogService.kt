package com.fatalzero.rsandroidfinal_task.utils

import android.app.AlertDialog
import android.content.Context
import com.fatalzero.rsandroidfinal_task.utils.dialog.CurrentActivity
import com.fatalzero.rsandroidfinal_task.utils.dialog.DialogFactory
import javax.inject.Inject

class DialogService @Inject constructor(private var dialogFactory: DialogFactory) {

    fun showDialog(message: String, action: () -> Unit) {
        dialogFactory.getDialog()?.let {
            it.setMessage(message)?.setPositiveButton(android.R.string.ok) { _, _ ->
                action()
            }?.show()
        }
    }
}

