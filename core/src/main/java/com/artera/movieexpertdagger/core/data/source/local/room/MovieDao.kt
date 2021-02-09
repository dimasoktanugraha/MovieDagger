package com.artera.movieexpertdagger.core.data.source.local.room

import androidx.room.*
import com.artera.movieexpertdagger.core.data.source.local.entity.MovieEntity
import com.artera.movieexpertdagger.core.data.source.local.entity.TvEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getAllMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie where isFavorite = 1")
    fun getFavoriteMovie(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(tourism: List<MovieEntity>)

    @Update
    fun updateFavoriteMovie(tourism: MovieEntity)


    @Query("SELECT * FROM tv")
    fun getAllTv(): Flow<List<TvEntity>>

    @Query("SELECT * FROM tv where isFavorite = 1")
    fun getFavoriteTv(): Flow<List<TvEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTv(tourism: List<TvEntity>)

    @Update
    fun updateFavoriteTv(tourism: TvEntity)

}