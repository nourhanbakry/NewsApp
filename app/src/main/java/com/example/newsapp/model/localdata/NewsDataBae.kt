package com.example.newsapp.model.localdata

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapp.model.entity.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class NewsDataBase : RoomDatabase(){
    abstract fun newsDAO(): NewsDAO
    companion object{
        @Volatile
        private var NEWS_DB_INSTANCE: NewsDataBase? = null


        fun getNewsDBInstance(context: Context): NewsDataBase{
            val tempInstance = NEWS_DB_INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        NewsDataBase::class.java,
                        "new_database")
                    .build()

                NEWS_DB_INSTANCE= instance
                return NEWS_DB_INSTANCE!!
            }
        }
    }
}


