package com.fatalzero.rsandroidfinal_task.data.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface JokeDao {
    @Query("SELECT * FROM jokedbmodel")
    fun getJokes(): LiveData<List<JokeDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJoke(joke: JokeDbModel)

    @Delete
    suspend fun deleteJoke(joke: JokeDbModel)

    @Query("SELECT * FROM jokedbmodel WHERE id=:id")
    suspend fun getJoke(id:String):JokeDbModel

    @Query("SELECT * FROM jokedbmodel WHERE (category IN (:filters) AND joke like :searchQuery) COLLATE NOCASE")
    fun search(searchQuery:String,filters:List<String>):LiveData<List<JokeDbModel>>

}
