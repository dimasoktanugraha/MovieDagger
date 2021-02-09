package com.artera.movieexpertdagger.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(

    @field:SerializedName("id")
    var movieId: Int,

    @field:SerializedName("title")
    var title: String,

    @field:SerializedName("overview")
    var overview: String,

    @field:SerializedName("vote_average")
    var vote_average: Float,

    @field:SerializedName("release_date")
    var release_date: String,

    @field:SerializedName("poster_path")
    var poster_path: String,

    @field:SerializedName("backdrop_path")
    var backdrop_path: String
)
