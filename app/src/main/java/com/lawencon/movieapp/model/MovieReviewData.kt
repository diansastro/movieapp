package com.lawencon.movieapp.model

import com.google.gson.annotations.SerializedName

data class MovieReviewData(@SerializedName("author") var author: String? = "",
                           @SerializedName("author_details") var author_details: AuthorDetailsData? = null,
                           @SerializedName("content") var content: String? = "",
                           @SerializedName("created_at") var created_at: String? = "",
                           @SerializedName("id") var id: String? = "",
                           @SerializedName("updated_at") var updated_at: String? = "",
                           @SerializedName("url") var url: String? = "") {
}