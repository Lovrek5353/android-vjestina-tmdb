package com.example.tmdb.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName

@Entity(tableName = "movie_genre")
data class GenreEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "genre_id")
    val genreId: Int,
    @SerialName("genre_name")
    val genreName: String
)