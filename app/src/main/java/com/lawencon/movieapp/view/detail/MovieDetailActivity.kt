package com.lawencon.movieapp.view.detail

import android.app.Activity
import com.lawencon.movieapp.R
import com.lawencon.movieapp.base.BaseMvpActivity
import com.lawencon.movieapp.data.response.MovieTrailerResponse
import com.lawencon.movieapp.model.MovieTrailerData
import com.lawencon.movieapp.objects.Params
import com.lawencon.movieapp.view.movie.MovieListActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import org.jetbrains.anko.intentFor
import javax.inject.Inject

open class MovieDetailActivity: BaseMvpActivity<MovieDetailPresenter>(), HasAndroidInjector, MovieDetailContract.View {

    companion object {
        fun newInstance(activity: Activity, movieId: Int) {
            activity.startActivity(activity.intentFor<MovieListActivity>(Params.MOVIE_ID to movieId))
        }
    }

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override lateinit var presenter: MovieDetailPresenter

    private var movieId: Int = 0
    private val mData = arrayListOf<MovieTrailerData>()

    override fun initPresenterView() {
        presenter.view = this
    }

    override fun injectView() {
        AndroidInjection.inject(this)
    }

    override fun setup() {
        showLoading()
        initBundle()
        presenter.execMovieTrailer(movieId)
    }

    override fun getLayout(): Int = R .layout.activity_movie_detail

    @Inject
    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount == 0) super.onBackPressed()
        else supportFragmentManager.popBackStack()
    }

    private fun initBundle() {
        intent?.extras?.let {
            movieId = it.getInt(Params.MOVIE_ID)
        }
    }

    override fun getMovieTrailer(movieTrailerResponse: MovieTrailerResponse?) {
        TODO("Not yet implemented")
    }
}