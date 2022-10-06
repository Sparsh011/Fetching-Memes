package com.example.fetchingmemes.model.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: MemeInterface by lazy {
        Retrofit.Builder().baseUrl("https://meme-api.herokuapp.com/gimme/")
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(MemeInterface::class.java)
    }
}