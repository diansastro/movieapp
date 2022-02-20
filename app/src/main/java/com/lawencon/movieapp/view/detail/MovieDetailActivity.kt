package com.lawencon.movieapp.view.detail

import android.app.Activity
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import com.lawencon.movieapp.R
import com.lawencon.movieapp.base.BaseMvpActivity
import com.lawencon.movieapp.data.response.MovieReviewResponse
import com.lawencon.movieapp.data.response.MovieTrailerResponse
import com.lawencon.movieapp.model.MovieReviewData
import com.lawencon.movieapp.model.MovieTrailerData
import com.lawencon.movieapp.objects.Params
import com.lawencon.movieapp.view.adapter.ReviewAdapter
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import kotlinx.android.synthetic.main.activity_movie_detail.*
import org.jetbrains.anko.intentFor
import javax.inject.Inject

open class MovieDetailActivity: BaseMvpActivity<MovieDetailPresenter>(), MovieDetailContract.View, YouTubePlayer.OnInitializedListener {

    companion object {
        fun newInstance(activity: Activity, movieId: Int) {
            activity.startActivity(activity.intentFor<MovieDetailActivity>(Params.MOVIE_ID to movieId))
        }
    }

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    override lateinit var presenter: MovieDetailPresenter

    private var movieId: Int = 0
    private lateinit var rAdapter: ReviewAdapter
    private val mData = arrayListOf<MovieTrailerData>()
    private val rData = arrayListOf<MovieReviewData>()
    private val RECOVERY_DIALOG_REQUEST = 1
    private var videoUrl: String? = ""

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
        presenter.execMovieReview(movieId)
        initView()
    }

    override fun getLayout(): Int = R .layout.activity_movie_detail

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount == 0) super.onBackPressed()
        else supportFragmentManager.popBackStack()
    }

    private fun initBundle() {
        intent?.extras?.let {
            movieId = it.getInt(Params.MOVIE_ID)
        }
    }

    private fun initView() {
        val youtubeFragment = supportFragmentManager.findFragmentById(R.id.ytPlayer) as YouTubePlayerSupportFragment?
        youtubeFragment?.initialize("AIzaSyC09yy5vASiOlNHfHRRkbgaHSVnmuuq04g", this)
    }

    override fun getMovieTrailer(movieTrailerResponse: MovieTrailerResponse?) {
        if (movieTrailerResponse?.results?.size!! > 0) {
            mData.addAll(movieTrailerResponse.results)
        }

        movieTrailerResponse.results.forEach {
            videoUrl = it.key
        }

        dismissLoading()
    }

    override fun getMovieReview(movieReviewResponse: MovieReviewResponse?) {
        if (movieReviewResponse?.results?.size!! > 0) {
            rData.addAll(movieReviewResponse.results)
        }

        rAdapter = ReviewAdapter(rData)
        rAdapter.apply { notifyDataSetChanged() }

        rvUserReview.apply {
            adapter = rAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            isNestedScrollingEnabled = false
        }

        rvUserReview.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        dismissLoading()
    }

    override fun onInitializationSuccess(provider: YouTubePlayer.Provider, youTubePlayer: YouTubePlayer, wasResored: Boolean) {
        if(!wasResored) {
            println("Video Url: $videoUrl")
            youTubePlayer.cueVideo(videoUrl)
        }
    }

    override fun onInitializationFailure(provider: YouTubePlayer.Provider,youTubeInitializationResult: YouTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError) {
            youTubeInitializationResult.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show()
        } else {
            val errorMessage = String.format("There was an error initializing the YouTubePlayer (%1\$s)", youTubeInitializationResult.toString())
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        }
    }
}