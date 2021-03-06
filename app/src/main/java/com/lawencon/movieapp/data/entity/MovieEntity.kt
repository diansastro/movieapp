package com.lawencon.movieapp.data.entity

import android.content.Context
import com.lawencon.movieapp.data.BasicAbstractNetwork
import com.lawencon.movieapp.data.api.MovieApi
import com.lawencon.movieapp.data.response.GenreListResponse
import com.lawencon.movieapp.data.response.MovieByGenreResponse
import com.lawencon.movieapp.data.response.MovieReviewResponse
import com.lawencon.movieapp.data.response.MovieTrailerResponse
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject

class MovieEntity @Inject constructor(context: Context): BasicAbstractNetwork<MovieApi>(context) {
    override fun getApi(): Class<MovieApi> = MovieApi::class.java

    fun getGenreList(): Observable<Response<GenreListResponse>> = networkService().getGenreList()

    fun getMovieByGenre(genreId: Int): Observable<Response<MovieByGenreResponse>> = networkService().getMovieByGenre(genreId)

    fun getMovieTrailer(movieId: Int): Observable<Response<MovieTrailerResponse>> = networkService().getMovieTrailer(movieId)

    fun getMovieReview(movieId: Int): Observable<Response<MovieReviewResponse>> = networkService().getMovieReview(movieId)
}