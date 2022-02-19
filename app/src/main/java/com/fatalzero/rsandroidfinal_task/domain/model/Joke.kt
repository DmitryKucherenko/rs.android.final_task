package com.fatalzero.rsandroidfinal_task.domain.model

import com.fatalzero.rsandroidfinal_task.utils.Constants.UNDEFINED_ID


data class Joke(
    val joke: String="",
    val id: String = UNDEFINED_ID,
    val category: String=""
)