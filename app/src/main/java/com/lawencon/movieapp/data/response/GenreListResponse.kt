package com.lawencon.movieapp.data.response

import com.google.gson.annotations.SerializedName
import com.lawencon.movieapp.model.GenreListData

class GenreListResponse(@SerializedName("genres") var genres: ArrayList<GenreListData>) {
}