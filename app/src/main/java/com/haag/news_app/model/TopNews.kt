package com.haag.news_app.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TopNews(
    @SerializedName("status")
    @Expose
    var status: String,

    @SerializedName("totalResults")
    @Expose
    var totalResults: String,

    @SerializedName("articles")
    @Expose
    val articles: Article
)