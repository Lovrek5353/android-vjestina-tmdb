package com.example.tmdb.database

import androidx.room.ColumnInfo


data class MovieCardEntity(
    @ColumnInfo(name = "movie_id")
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "overview")
    val overview: String,
    @ColumnInfo(name = "favorite")
    val favorite: Boolean,
    @ColumnInfo(name = "poster_path")
    val posterPath: String
)
