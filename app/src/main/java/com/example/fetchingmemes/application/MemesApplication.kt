package com.example.fetchingmemes.application

import android.app.Application
import com.example.fetchingmemes.model.database.MemesDatabase
import com.example.fetchingmemes.model.database.MemesRepository

class MemesApplication : Application(){
    //    This application is used to define the variable scopes that will be used throughout the application. We can setup our database and repository here. Using lazy because we don't want db to be loaded when app is started, but when it is needed.
    private val database by lazy{
        MemesDatabase.getDatabase(this@MemesApplication)
    }
    val repository by lazy {
        MemesRepository(database.memeDao())
    }
}