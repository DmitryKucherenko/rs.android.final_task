package com.fatalzero.rsandroidfinal_task.domain.model


data class Joke(
    val joke: String?,
    val id: Int? = UNDEFINED_ID,
    val category: String?
) {
    companion object {
        const val UNDEFINED_ID = -1
    }
}