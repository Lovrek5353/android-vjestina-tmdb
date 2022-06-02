package com.example.tmdb.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        ActorEntity::class,
        CrewEntity::class,
        MovieEntity::class,
        GenreEntity::class,
        MovieGenreCrossRef::class,
        MovieCrewCrossRef::class,
        MovieActorCrossRef::class,
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}