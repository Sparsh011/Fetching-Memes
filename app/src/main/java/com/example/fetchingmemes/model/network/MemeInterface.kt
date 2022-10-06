package com.example.fetchingmemes.model.network

import com.example.fetchingmemes.model.entities.NewMemeModel
import retrofit2.Response
import retrofit2.http.GET

interface MemeInterface {
    @GET("memes")
    suspend fun getMemes(): Response<NewMemeModel.MemeClass> // Using the suspend keyword so that data is not fetched on the main thread and it is executed on coroutine
}