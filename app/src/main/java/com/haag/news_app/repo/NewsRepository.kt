package com.haag.news_app.repo

import com.haag.news_app.model.ArticleResponse
import com.haag.news_app.network.NewsAPI
import com.haag.news_app.room.ArticleDao
import com.haag.news_app.room.Read
import com.haag.news_app.utils.Constants
import retrofit2.Response
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsAPI: NewsAPI,
    private val dao: ArticleDao
) {

    suspend fun getTopNews(): Response<ArticleResponse> =
        newsAPI.getTopNews(Constants.COUNTRY, Constants.API_KEY)

    suspend fun insertTitleDao(title: Read) {
        dao.insert(title)
    }

    suspend fun getTitleList(): List<Read> =
        dao.getTitleList()
}