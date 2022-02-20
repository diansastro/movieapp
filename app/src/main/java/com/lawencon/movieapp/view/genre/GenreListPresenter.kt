package com.lawencon.movieapp.view.genre

import com.lawencon.movieapp.base.BasePresenter
import com.lawencon.movieapp.data.entity.MovieEntity
import com.lawencon.movieapp.extension.uiSubscribe
import com.lawencon.movieapp.objects.NetworkCode
import javax.inject.Inject

class GenreListPresenter @Inject constructor(val movieEntity: MovieEntity): BasePresenter<GenreListContract.View>(), GenreListContract.Presenter {
    override fun execGenreList() {
        addSubscription(movieEntity.getGenreList().uiSubscribe({
           when(it.code()) {
               NetworkCode.CODE_OK -> view?.getGenreList(it.body())
               else -> {
                   view?.errorScreen("Unable to Load Data")
               }
           }
        }, {
           view?.errorScreen(it.localizedMessage)
        }, {}))
    }
}