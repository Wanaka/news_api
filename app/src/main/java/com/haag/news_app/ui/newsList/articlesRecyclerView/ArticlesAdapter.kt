package com.haag.news_app.ui.newsList.articlesRecyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.haag.news_app.R
import com.haag.news_app.model.Article
import com.haag.news_app.room.Read
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.articles_item.view.*

class ArticlesAdapter(
    private val list: List<Article>,
    private val titleList: List<Read>,
    private val context: Context,
    private val mListener: OnItemClickListener?
) :
    RecyclerView.Adapter<ArticlesViewHolder>() {

    interface OnItemClickListener {
        fun onItemChatClick(pos: Int, title: Read)
    }

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
        return ArticlesViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.articles_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        val article: Article = list[position]
        var isNew = true

        holder.itemView.setOnClickListener {
            mListener!!.onItemChatClick(position, Read(article.title))
        }

        titleList.forEach {
            if (article.title == it.title) isNew = false
        }

        holder.bind(article, isNew)
    }
}


class ArticlesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private var article = view.articleTitle
    private var articleImage = view.articleImage
    private var newArticle = view.newIcon

    fun bind(a: Article, isNew: Boolean) {
        article.text = a.title
        Picasso.get().load(a.urlToImage).placeholder(R.drawable.default_image)
            .into(articleImage)
        if (!isNew) newArticle.visibility = View.GONE
    }
}