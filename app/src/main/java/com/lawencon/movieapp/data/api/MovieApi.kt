package com.lawencon.movieapp.data.api

import com.lawencon.movieapp.data.response.GenreListResponse
import com.lawencon.movieapp.data.response.MovieByGenreResponse
import com.lawencon.movieapp.data.response.MovieTrailerResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("genre/movie/list?api_key=0f6683dfde9dcd6dd2cca8d62b969728")
    fun getGenreList(): Observable<Response<GenreListResponse>>

    @GET("discover/movie?api_key=0f6683dfde9dcd6dd2cca8d62b969728")
    fun getMovieByGenre(@Query("with_genres") genreId: Int): Observable<Response<MovieByGenreResponse>>

    @GET("movie/{movie_id}/videos?api_key=0f6683dfde9dcd6dd2cca8d62b969728")
    fun getMovieTrailer(@Path("movie_id") movieId: Int): Observable<Response<MovieTrailerResponse>>

}