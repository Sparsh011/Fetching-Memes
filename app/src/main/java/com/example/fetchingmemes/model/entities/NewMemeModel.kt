package com.example.fetchingmemes.model.entities

object NewMemeModel{
    data class MemeClass(
        val author: String,
        val nsfw: Boolean,
        val postLink: String,
        val preview: List<String>,
        val spoiler: Boolean,
        val subreddit: String,
        val title: String,
        val ups: Int,
        val url: String
    )
}