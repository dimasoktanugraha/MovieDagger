package com.artera.movieexpertdagger.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvRawResponse (

    @field:SerializedName("results")
    var results: List<TvResponse>

    )