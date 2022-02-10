package com.fatalzero.rsandroidfinal_task.utils

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import com.fatalzero.rsandroidfinal_task.domain.model.Joke
import com.fatalzero.rsandroidfinal_task.domain.usecase.SendJoke

import javax.inject.Inject





class SendJokeImpl @Inject constructor(val context: Context) : SendJoke {

    private fun getBodyMessage(
        joke: Joke?
    ): String {
        return with(StringBuilder()) {
            appendLine("Joke category:${joke?.category}")
            appendLine("${joke?.joke}")
            toString()
        }
    }

    override fun sendJoke(joke: Joke?) {
        //внутреняя функция формирующая текст письма
        val emailIntent = Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_SUBJECT, "Funny Joke")
            putExtra(
                Intent.EXTRA_TEXT,
                getBodyMessage(
                    joke
                )
            )
            flags = Intent.FLAG_ACTIVITY_NEW_TASK;
            type = "text/plain"
        }
        //передаем интент
        startActivity(context, emailIntent, null)
    }

}
