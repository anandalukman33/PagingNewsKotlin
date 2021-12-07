package com.example.pagingnewskotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingnewskotlin.R
import com.example.pagingnewskotlin.model.ArticlesItem
import com.squareup.picasso.Picasso

class NewsListAdapter: PagedListAdapter<ArticlesItem, RecyclerView.ViewHolder>(ArticlesItem().DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)

        return NewsHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is NewsHolder) {
            holder.bindTo(getItem(position))
        }
    }

    class NewsHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView? = null
        var tvTime: TextView? = null
        var tvDesc: TextView? = null
        var ivImage: ImageView? = null
        var ivLikes: ImageView? = null
        var tvLikes: TextView? = null
        var tvComments: TextView? = null
        var tvShare: TextView? = null

        fun bindTo(item: ArticlesItem?) {
            tvTitle = itemView.findViewById(R.id.item_title)
            tvTime = itemView.findViewById(R.id.item_time)
            tvDesc = itemView.findViewById(R.id.item_desc)
            ivImage = itemView.findViewById(R.id.item_image)
            ivLikes = itemView.findViewById(R.id.img_likes)
            tvLikes = itemView.findViewById(R.id.item_likes)
            tvComments = itemView.findViewById(R.id.item_comments)
            tvShare = itemView.findViewById(R.id.item_shares)

            tvTitle?.text = item?.title
            tvTime?.text = item?.publishedAt
            tvDesc?.text = item?.description
            Picasso.get().load(item?.urlToImage).into(ivImage)
        }

    }
}