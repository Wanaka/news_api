package com.haag.news_app.ui.article

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.haag.news_app.model.Article
import com.haag.news_app.repo.NewsRepository


class ArticleViewModel @ViewModelInject constructor(private val repo: NewsRepository) :
    ViewModel() {

    fun getArticleDetails(articlePos: Int): LiveData<Article> = liveData {
        try {
            val response = repo.getTopNews()

            response.body()?.articles?.get(articlePos)?.let {
                emit(it)
            }

        } catch (e: Exception) {
            Log.d("ArticleViewModel", "Exception: $e")
        }
    }


    fun openWebView(context: Context, url: String?) {
        startActivity(context, Intent(Intent.ACTION_VIEW, Uri.parse(url)), null)
    }
}