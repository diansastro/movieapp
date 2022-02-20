package com.lawencon.movieapp.view.adapter

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lawencon.movieapp.R
import com.lawencon.movieapp.model.GenreData
import kotlinx.android.synthetic.main.item_movie_by_genre.view.*

class MovieByGenreAdapter(var list: List<GenreData>): BaseQuickAdapter<GenreData, BaseViewHolder>(R.layout.item_movie_by_genre, list) {
    override fun convert(helper: BaseViewHolder?, item: GenreData?) {
        val backdropPath = "https://image.tmdb.org/t/p/original"
        helper?.let { h ->
            item?.let { i ->
                if (!i.backdrop_path.isNullOrBlank()) {
                    Glide.with(mContext).load(backdropPath.plus(i.backdrop_path)).into(h.itemView.thumbnail)
                }

                h.itemView.title.text = i.title
                h.itemView.release.text = mContext.getString(R.string.release, i.release_date)
                h.itemView.vote.text = mContext.getString(R.string.vote, i.vote_average)
                h.itemView.rating.text = mContext.getString(R.string.rating, i.vote_count.toString())
            }
        }
    }
}