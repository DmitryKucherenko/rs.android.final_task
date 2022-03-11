package com.fatalzero.rsandroidfinal_task.utils

import android.os.SystemClock
import android.util.Base64
import com.fatalzero.rsandroidfinal_task.domain.model.Joke
import kotlin.random.Random

object GenID {
    fun generateId(): String {
        val genByteArray =
            (SystemClock.elapsedRealtime() + Random.nextInt(0, 1000)).toString().toByteArray()
        return Base64.encodeToString(genByteArray, Base64.DEFAULT)
    }
}