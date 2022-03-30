package com.fatalzero.rsandroidfinal_task.utils

import android.content.Context
import android.widget.Toast


class ShowMessage (private var context: Context) {
    operator fun invoke(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show()
    }
}