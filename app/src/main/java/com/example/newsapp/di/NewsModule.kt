package com.example.newsapp.di

import android.app.Application
import android.content.Context
import com.example.newsapp.model.localdata.NewsDataBase
import com.example.newsapp.model.reposotry.NewsRepositry
import com.example.newsapp.viewmodel.NewsViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NewsModule {
    @Provides
    @Singleton
    fun provideNewsDataBase(@ApplicationContext context: Context): NewsDataBase{
        return NewsDataBase.getNewsDBInstance(context)
    }

    @Provides
    @Singleton
    fun provideNewsReposatry(newsDataBase: NewsDataBase,@ApplicationContext context: Context) : NewsRepositry{
       return  NewsRepositry(newsDataBase, context as Application)
    }



    @Provides
    @Singleton
    fun provideNewsViewModel(newsRepositry: NewsRepositry): NewsViewModel{
        return NewsViewModel(newsRepositry)
    }
}