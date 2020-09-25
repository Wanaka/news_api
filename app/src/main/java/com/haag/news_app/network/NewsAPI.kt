package com.haag.news_app.network

import com.haag.news_app.model.ArticleResponse
import com.haag.news_app.model.TopNews
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("top-headlines")
    suspend fun getTopNews(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Response<ArticleResponse>

//    @GET("categories")
//    suspend fun getTopNews(): Response<List<String>>

}
