package com.haag.news_app.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ArticleResponse (
    @SerializedName("status")
    @Expose
    val status: String,

    @SerializedName("source")
    @Expose
    val source: String,

    @SerializedName("sortBy")
    @Expose
    val sortBy: String,

    @SerializedName("articles")
    @Expose
    val articles: List<Article>
)