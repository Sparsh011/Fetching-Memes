package com.example.fetchingmemes.model.database

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.fetchingmemes.model.entities.Meme
import kotlinx.coroutines.flow.Flow

class MemesRepository(private val memesDao: MemesDao) {

    @WorkerThread
    suspend fun insertMemeURL(meme: Meme){
        memesDao.insertMemeDetails(meme)
    }

    val allMemes: LiveData<List<Meme>> = memesDao.getAllMemes()

    //    Updating data-
    @WorkerThread
    suspend fun updateMeme(meme: Meme){
        memesDao.updateMemeDetails(meme)
    }

    @WorkerThread
    suspend fun deleteMeme(meme: Meme){
//    This favDish is received from FavDishViewModel and then this favDish is passed to DAO
        memesDao.deleteMeme(meme)
    }
}