package com.haag.news_app.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article_table")
class Read(@PrimaryKey @ColumnInfo(name = "article") val title: String)