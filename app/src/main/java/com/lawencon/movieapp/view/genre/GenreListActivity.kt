package com.lawencon.movieapp.view.genre

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lawencon.movieapp.R
import com.lawencon.movieapp.base.BaseMvpActivity
import com.lawencon.movieapp.data.response.GenreListResponse
import com.lawencon.movieapp.model.GenreListData
import com.lawencon.movieapp.view.adapter.GenreListAdapter
import com.lawencon.movieapp.view.movie.MovieListActivity
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import kotlinx.android.synthetic.main.activity_genre_list.*
import javax.inject.Inject

open class GenreListActivity: BaseMvpActivity<GenreListPresenter>(), GenreListContract.View {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    override lateinit var presenter: GenreListPresenter

    private lateinit var gAdapter: GenreListAdapter
    private val gData = arrayListOf<GenreListData>()

    override fun initPresenterView() {
        presenter.view = this
    }

    override fun injectView() {
        AndroidInjection.inject(this)
    }

    override fun setup() {
        showLoading()
        presenter.execGenreList()
    }

    override fun getLayout(): Int = R.layout.activity_genre_list

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount == 0) super.onBackPressed()
        else supportFragmentManager.popBackStack()
    }

    override fun getGenreList(genreListResponse: GenreListResponse?) {
        if (genreListResponse?.genres?.size!! > 0) {
            gData.addAll(genreListResponse.genres)
        }

        gAdapter = GenreListAdapter(gData)
        gAdapter.apply {
            setOnItemClickListener { adapter, view, position ->
                gData[position].id?.let {
                    MovieListActivity.newInstance(this@GenreListActivity, it)
                }
            }
            notifyDataSetChanged()
        }

        rvGenreList.apply {
            adapter = gAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            isNestedScrollingEnabled = false
        }

        rvGenreList.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        dismissLoading()
    }
}