package com.lawencon.movieapp.data.api

import com.lawencon.movieapp.data.response.GenreListResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

interface MovieApi {

    @GET("genre/movie/list?api_key=0f6683dfde9dcd6dd2cca8d62b969728")
    fun getGenreList(): Observable<Response<GenreListResponse>>

}