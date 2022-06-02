package com.example.tmdb.database

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["movie_id", "crew_id"])
data class MovieCrewCrossRef(
    @ColumnInfo(name = "movie_id")
    val movieID: Int,
    @ColumnInfo(name = "crew_id")
    val crewID: Int
)

@Entity(primaryKeys = ["movie_id", "actor_id"])
data class MovieActorCrossRef(
    @ColumnInfo(name = "movie_id")
    val movie_ID: Int,
    @ColumnInfo(name = "actor_id")
    val actor_ID: Int
)

@Entity(primaryKeys = ["movie_id", "genre_id"])
data class MovieGenreCrossRef(
    @ColumnInfo(name = "movie_id")
    val movieId: Int,
    @ColumnInfo(name = "genre_id")
    val genreId: Int
)