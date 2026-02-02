package com.example.newsapp.model.localdata

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.model.entity.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface NewsDAO {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun upsertNews(article: Article): Long

    @Query("Select * from articles")
    fun getAllArticles(): Flow<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)
}