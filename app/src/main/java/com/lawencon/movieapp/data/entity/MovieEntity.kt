package com.lawencon.movieapp.data.entity

import android.content.Context
import com.lawencon.movieapp.data.BasicAbstractNetwork
import com.lawencon.movieapp.data.api.MovieApi
import com.lawencon.movieapp.data.response.GenreListResponse
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject

class MovieEntity @Inject constructor(context: Context): BasicAbstractNetwork<MovieApi>(context) {
    override fun getApi(): Class<MovieApi> = MovieApi::class.java

    fun getGenreList(): Observable<Response<GenreListResponse>> = networkService().getGenreList()
}