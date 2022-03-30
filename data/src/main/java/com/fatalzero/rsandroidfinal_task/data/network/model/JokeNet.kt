package com.fatalzero.rsandroidfinal_task.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JokeNet(

    @Json(name = "delivery")
    val delivery: String?,

    @Json(name = "flags")
    val flags: Flags?,

    @Json(name = "safe")
    val safe: Boolean?,

    @Json(name = "setup")
    val setup: String?,

    @Json(name = "joke")
    val joke: String?,

    @Json(name = "id")
    val id: String?,

    @Json(name = "category")
    val category: String?,

    @Json(name = "type")
    val type: String?,

    @Json(name = "lang")
    val lang: String?
)
