package com.fatalzero.rsandroidfinal_task.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery


@Dao
interface JokeDao {
    @Query("SELECT * FROM jokedbmodel")
    fun getJokes(): LiveData<List<JokeDbModel>>

    @Query("SELECT category FROM jokedbmodel GROUP BY category")
    fun getCategories():LiveData<List<String>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJoke(joke: JokeDbModel)

    @Delete
    suspend fun deleteJoke(joke: JokeDbModel)

    @Query("SELECT * FROM jokedbmodel WHERE id=:id")
    suspend fun getJoke(id:String):JokeDbModel

    @RawQuery (observedEntities = [JokeDbModel::class])
    fun search(searchQuery: SupportSQLiteQuery):LiveData<List<JokeDbModel>>

}
