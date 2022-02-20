package com.lawencon.movieapp.data.response

import com.google.gson.annotations.SerializedName
import com.lawencon.movieapp.model.MovieReviewData

class MovieReviewResponse(@SerializedName("id") var id: Int? = 0,
                          @SerializedName("page") var page: Int? = 0,
                          @SerializedName("results") var results: ArrayList<MovieReviewData>) {
}