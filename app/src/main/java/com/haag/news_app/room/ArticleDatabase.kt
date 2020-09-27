package com.haag.news_app.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Read::class],
    version = 1
)
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun getDao(): ArticleDao
}