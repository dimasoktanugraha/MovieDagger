package com.artera.movieexpertdagger.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    var movieId: Int,
    var title: String,
    var overview: String,
    var vote_average: Float,
    var release_date: String,
    var poster_path: String,
    var backdrop_path: String,
    var isFavorite: Boolean
): Parcelable
