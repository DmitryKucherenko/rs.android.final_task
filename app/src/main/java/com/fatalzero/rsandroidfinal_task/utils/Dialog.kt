package com.fatalzero.rsandroidfinal_task.utils

import android.app.Activity
import android.app.AlertDialog


class JokeDialog(var activity: Activity,message:String) {
    val dialog = AlertDialog.Builder(activity)
        .setMessage(message)
        .setNegativeButton(android.R.string.cancel) { _, _ ->
        }
        .setPositiveButton(android.R.string.ok) { _, _ ->
        }

    operator fun invoke(action: () -> Unit) {
        dialog.setPositiveButton(android.R.string.ok) { _, _ ->
            action()
        }.show()
    }

}