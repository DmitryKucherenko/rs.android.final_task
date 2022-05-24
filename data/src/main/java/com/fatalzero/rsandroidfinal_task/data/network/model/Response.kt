package com.fatalzero.rsandroidfinal_task.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Response(

    @Json(name = "amount")
    val amount: Int?,

    @Json(name = "error")
    val error: Boolean?,

    @Json(name = "jokes")
    val jokes: List<JokeNet>?
)


