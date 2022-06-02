package com.example.tmdb.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "actor")
data class ActorEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "actor_id")
    val actor_id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "carachterName")
    val carachterName: String,
    @ColumnInfo(name = "image_path")
    val image_path: String?
)