package com.example.tmdb.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "crewMember")
data class CrewEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "crew_id")
    val crew_id: Int,
    @ColumnInfo(name = "crew_name")
    val crew_name: String,
    @ColumnInfo(name = "job")
    val job: String,
)