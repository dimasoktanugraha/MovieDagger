package com.artera.movieexpertdagger.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv")
data class TvEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tvId")
    var tvId: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "vote_average")
    var vote_average: Float?,

    @ColumnInfo(name = "first_air_date")
    var first_air_date: String,

    @ColumnInfo(name = "poster_path")
    var poster_path: String,

    @ColumnInfo(name = "backdrop_path")
    var backdrop_path: String?,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)
