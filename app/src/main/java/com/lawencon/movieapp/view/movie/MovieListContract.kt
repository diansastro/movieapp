package com.lawencon.movieapp.view.movie

import com.lawencon.movieapp.base.ErrorView
import com.lawencon.movieapp.data.response.MovieByGenreResponse

interface MovieListContract {
    interface View: ErrorView {
        fun getMovieByGenre(movieByGenreResponse: MovieByGenreResponse?)
    }

    interface Presenter {
        fun execMovieByGenre(genreId: Int)
    }
}