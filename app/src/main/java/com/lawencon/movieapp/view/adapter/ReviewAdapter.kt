package com.lawencon.movieapp.view.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lawencon.movieapp.R
import com.lawencon.movieapp.model.MovieReviewData
import kotlinx.android.synthetic.main.item_user_review.view.*

class ReviewAdapter(var list: List<MovieReviewData>): BaseQuickAdapter<MovieReviewData, BaseViewHolder>(R.layout.item_user_review, list) {
    override fun convert(helper: BaseViewHolder?, item: MovieReviewData?) {
        helper?.let { h ->
            item?.let { i ->
                h.itemView.tvAuthor.text = mContext.getString(R.string.review_by, i.author)
                h.itemView.tvReviewDate.text = i.updated_at
                h.itemView.tvReviewContent.text = i.content
            }
        }
    }
}