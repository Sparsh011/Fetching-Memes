package com.example.fetchingmemes.model.entities
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "memes_table")
data class Meme (
    @ColumnInfo val image: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
    ) : Parcelable