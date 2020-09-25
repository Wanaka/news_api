package com.haag.news_app.ui.articlesRecyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.haag.news_app.R
import com.haag.news_app.model.Article
import com.haag.news_app.model.ArticleResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.articles_item.view.*

class ArticlesAdapter(
    private val list: List<Article>,
    private val context: Context,
    private val mListener: OnItemClickListener?
) :
    RecyclerView.Adapter<ArticlesViewHolder>() {

    interface OnItemClickListener {
        fun onItemChatClick(article: Article)
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
        holder.bind(article)

        holder.itemView.setOnClickListener {
            mListener!!.onItemChatClick(article)
        }
    }
}


class ArticlesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private var userName = view.articleTitle
    private var articleImage = view.articleImage

    fun bind(article: Article) {
        userName.text = article.title
        Picasso.get().load(article.urlToImage).into(articleImage)

    }
}