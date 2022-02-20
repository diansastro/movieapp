package com.lawencon.movieapp.view.movie

import com.lawencon.movieapp.base.BasePresenter
import com.lawencon.movieapp.data.entity.MovieEntity
import com.lawencon.movieapp.extension.uiSubscribe
import com.lawencon.movieapp.objects.NetworkCode
import javax.inject.Inject

class MovieListPresenter @Inject constructor(val movieEntity: MovieEntity): BasePresenter<MovieListContract.View>(), MovieListContract.Presenter {
    override fun execMovieByGenre(genreId: Int) {
        addSubscription(movieEntity.getMovieByGenre(genreId).uiSubscribe({
            when(it.code()) {
                NetworkCode.CODE_OK -> view?.getMovieByGenre(it.body())
                else -> {
                    view?.errorScreen("Unable to Fetch Data")
                }
            }
        }, {
           view?.errorScreen(it.localizedMessage)
        }, {}))
    }

}