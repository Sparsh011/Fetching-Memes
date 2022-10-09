package com.example.fetchingmemes.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fetchingmemes.model.entities.Meme

@Database(entities = [Meme::class], version = 1)
abstract class MemesDatabase : RoomDatabase() {

    abstract fun memeDao(): MemesDao

    companion object {
        @Volatile
        private var INSTANCE: MemesDatabase? = null

        fun getDatabase(context: Context): MemesDatabase {
//            If the instance is null, then create the database, and if it is not null, then return it.
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MemesDatabase::class.java,
                    "memes_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}