package com.haag.news_app.ui

import android.util.Log.d
import com.haag.news_app.model.ArticleResponse
import com.haag.news_app.model.TopNews
import com.haag.news_app.network.NewsAPI
import retrofit2.Call
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsAPI: NewsAPI) {

    fun helloFromRepo() = "repo"

    suspend fun getTopNews(): Response<ArticleResponse> {
        return newsAPI.getTopNews("nl", "05588972af7943188910742037f61d0f")
        //    ?country=us&apiKey=05588972af7943188910742037f61d0f

    }
}