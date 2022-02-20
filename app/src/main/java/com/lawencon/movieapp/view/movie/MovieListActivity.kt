package com.lawencon.movieapp.view.movie

import android.app.Activity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lawencon.movieapp.R
import com.lawencon.movieapp.base.BaseMvpActivity
import com.lawencon.movieapp.data.response.MovieByGenreResponse
import com.lawencon.movieapp.model.GenreData
import com.lawencon.movieapp.objects.Params
import com.lawencon.movieapp.view.adapter.MovieByGenreAdapter
import com.lawencon.movieapp.view.detail.MovieDetailActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_movie_by_genre.*
import org.jetbrains.anko.intentFor
import javax.inject.Inject

open class MovieListActivity:BaseMvpActivity<MovieListPresenter>(), HasAndroidInjector, MovieListContract.View {

    companion object {
        fun newInstance(activity: Activity, genreId: Int) {
            activity.startActivity(activity.intentFor<MovieListActivity>(Params.GENRE_ID to genreId))
        }
    }

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    override lateinit var presenter: MovieListPresenter

    private var genreId: Int = 0
    private lateinit var movieAdapter: MovieByGenreAdapter
    private val mData = arrayListOf<GenreData>()

    override fun initPresenterView() {
        presenter.view = this
    }

    override fun injectView() {
       AndroidInjection.inject(this)
    }

    override fun setup() {
        showLoading()
        initBundle()
        presenter.execMovieByGenre(genreId)
    }

    override fun getLayout(): Int = R.layout.activity_movie_by_genre

    @Inject
    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount == 0) super.onBackPressed()
        else supportFragmentManager.popBackStack()
    }

    private fun initBundle() {
        intent?.extras?.let {
            genreId = it.getInt(Params.GENRE_ID)
        }
    }

    override fun getMovieByGenre(movieByGenreResponse: MovieByGenreResponse?) {
        if (movieByGenreResponse?.results?.size!! > 0) {
            mData.addAll(movieByGenreResponse.results)
        }
        
        movieAdapter = MovieByGenreAdapter(mData)
        movieAdapter.apply { 
            setOnItemClickListener { adapter, view, position ->
                mData[position].id?.let {
                    MovieDetailActivity.newInstance(this@MovieListActivity, it)
                }
            }
            notifyDataSetChanged()
        }

        rvMovieList.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            isNestedScrollingEnabled = false
        }

        rvMovieList.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        dismissLoading()
    }
}