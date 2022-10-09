package com.example.fetchingmemes.model.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.fetchingmemes.model.entities.Meme
import kotlinx.coroutines.flow.Flow

@Dao
interface MemesDao {
    @Insert
    suspend fun insertMemeDetails(meme: Meme)

    @Query("SELECT * FROM MEMES_TABLE ORDER BY ID") // ID is the order in which meme was added to the table
    fun getAllMemes() : LiveData<List<Meme>>

    @Update
    suspend fun updateMemeDetails(meme: Meme)

    @Delete
    suspend fun deleteMeme(meme: Meme)
}