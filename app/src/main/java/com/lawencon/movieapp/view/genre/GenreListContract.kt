package com.lawencon.movieapp.view.genre

import com.lawencon.movieapp.base.ErrorView
import com.lawencon.movieapp.data.response.GenreListResponse

interface GenreListContract {
    interface View: ErrorView {
        fun getGenreList(genreListResponse: GenreListResponse?)
    }

    interface Presenter {
        fun execGenreList()
    }
}