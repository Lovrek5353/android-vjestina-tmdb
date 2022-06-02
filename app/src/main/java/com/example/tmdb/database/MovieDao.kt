package com.example.tmdb.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCastMember(castMember: ActorEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieActorCrossRef(actorCrossRef: MovieActorCrossRef)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCrewMember(crewEntity: CrewEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieCrewCrossRef(crewCrossRef: MovieCrewCrossRef)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGenre(genre: GenreEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGenreCrossRef(genreCrossRef: MovieGenreCrossRef)

    @Query("SELECT movie_id, title, poster_path, overview, favorite FROM movie")
    fun getFavouriteMovies(): MutableList<MovieCardEntity>

    @Query("SELECT * FROM movie WHERE movie_id= :movieId")
    fun getMovieDetails(movieId: Int): Flow<MovieEntityDetails>

    @Query("DELETE FROM movie WHERE movie_id = :movieId")
    fun removeMovie(movieId: Int)

}