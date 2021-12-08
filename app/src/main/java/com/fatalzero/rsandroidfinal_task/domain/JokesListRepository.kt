package com.fatalzero.rsandroidfinal_task.domain


import com.fatalzero.rsandroidfinal_task.data.network.model.JokesItem


interface JokesListRepository {
    suspend fun getJokesItem(count: Int, range: String): List<JokesItem>
}
