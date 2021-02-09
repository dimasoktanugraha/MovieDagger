package com.artera.movieexpertdagger.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvResponse(

    @field:SerializedName("id")
    var tvId: Int,

    @field:SerializedName("name")
    var name: String,

    @field:SerializedName("overview")
    var overview: String,

    @field:SerializedName("vote_average")
    var vote_average: Float?,

    @field:SerializedName("first_air_date")
    var first_air_date: String,

    @field:SerializedName("poster_path")
    var poster_path: String,

    @field:SerializedName("backdrop_path")
    var backdrop_path: String?
)
