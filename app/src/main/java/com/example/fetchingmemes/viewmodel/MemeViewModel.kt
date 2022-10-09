package com.example.fetchingmemes.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.fetchingmemes.model.database.MemesDatabase
import com.example.fetchingmemes.model.database.MemesRepository
import com.example.fetchingmemes.model.entities.Meme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MemeViewModel(application: Application) : AndroidViewModel(application) {
    val allMemes : LiveData<List<Meme>>
    val repository : MemesRepository

    init {
        val dao = MemesDatabase.getDatabase(application).memeDao()
        repository = MemesRepository(dao)
        allMemes = repository.allMemes
    }

    fun deleteNote(meme : Meme) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteMeme(meme)
    }

    fun updateNote(meme : Meme) = viewModelScope.launch (Dispatchers.IO){
        repository.updateMeme(meme)
    }

    fun insert(meme: Meme) = viewModelScope.launch (Dispatchers.IO){
        repository.insertMemeURL(meme)
    }
}
