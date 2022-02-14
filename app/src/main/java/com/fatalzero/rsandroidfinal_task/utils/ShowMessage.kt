package com.fatalzero.rsandroidfinal_task.utils

import android.content.Context
import android.widget.Toast
import javax.inject.Inject

class ShowMessage @Inject constructor(var context: Context) {
    operator fun invoke(text:String){
        Toast.makeText(context, text, Toast.LENGTH_LONG).show()
    }
}