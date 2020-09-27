package com.haag.news_app.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ArticleDao {

    @Query("SELECT * from article_table ORDER BY article ASC")
    suspend fun getTitleList(): List<Read>

    @Insert()
    suspend fun insert(title: Read)
}