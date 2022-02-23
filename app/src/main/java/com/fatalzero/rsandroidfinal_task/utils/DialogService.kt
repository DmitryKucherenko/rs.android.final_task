package com.fatalzero.rsandroidfinal_task.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.view.Window
import android.view.WindowManager
import com.fatalzero.rsandroidfinal_task.App
import javax.inject.Inject


class JokeDialog @Inject constructor(var context: Context) {

    val dialog = AlertDialog.Builder((context as App).getCurrentActivity())
         .setNegativeButton(android.R.string.cancel) { _, _ ->
        }
        .setPositiveButton(android.R.string.ok) { _, _ ->
        }

    operator fun invoke(message: String, action: () -> Unit) {
        dialog
            .setMessage(message)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                action()
            }.show()

    }

}