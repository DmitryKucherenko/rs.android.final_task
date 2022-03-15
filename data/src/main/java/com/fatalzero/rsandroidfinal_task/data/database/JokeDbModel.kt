package com.fatalzero.rsandroidfinal_task.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class JokeDbModel(
    val joke: String,
    @PrimaryKey
    val id: String,
    val category: String
)
