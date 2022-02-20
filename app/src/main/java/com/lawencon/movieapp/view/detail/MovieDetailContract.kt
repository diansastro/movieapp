package com.lawencon.movieapp.view.detail

import com.lawencon.movieapp.base.ErrorView
import com.lawencon.movieapp.data.response.MovieTrailerResponse

class MovieDetailContract {
    interface View: ErrorView {
        fun getMovieTrailer(movieTrailerResponse: MovieTrailerResponse?)
    }

    interface Presenter {
        fun execMovieTrailer(movieId: Int)
    }
}