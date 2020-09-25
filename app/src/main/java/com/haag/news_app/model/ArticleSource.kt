package com.haag.news_app.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ArticleSource(

    @SerializedName("id")
    @Expose
    val id: String,

    @SerializedName("name")
    @Expose
    val name: String
)