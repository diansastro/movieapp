package com.lawencon.movieapp.data.response

import com.google.gson.annotations.SerializedName
import com.lawencon.movieapp.model.GenreData

class MovieByGenreResponse(@SerializedName("page") var page: Int? = 0,
                           @SerializedName("results") var results: ArrayList<GenreData>,
                           @SerializedName("total_pages") var total_pages: Int? = 0,
                           @SerializedName("total_results") var total_results: Int? = 0) {
}