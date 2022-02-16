package com.fatalzero.rsandroidfinal_task.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fatalzero.rsandroidfinal_task.domain.model.Joke

@Database(entities = [JokeDbModel::class], version = 1, exportSchema = false)
abstract class AppDatabase() : RoomDatabase() {
    abstract fun jokeDao(): JokeDao

    companion object {

        private var db: AppDatabase? = null
        private const val DB_NAME = "joke.db"
        private val LOCK = Any()

        fun getInstance(context: Context): AppDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance =
                    Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        DB_NAME
                    ).build()
                db = instance
                return instance
            }
        }
    }
}