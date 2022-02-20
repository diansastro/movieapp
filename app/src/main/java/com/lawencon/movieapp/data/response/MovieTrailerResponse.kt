package com.lawencon.movieapp.data.response

import com.google.gson.annotations.SerializedName
import com.lawencon.movieapp.model.MovieTrailerData

class MovieTrailerResponse(@SerializedName("id") var id: Int? = 0,
                           @SerializedName("results") var results: ArrayList<MovieTrailerData>) {
}