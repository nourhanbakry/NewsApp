package com.example.newsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.model.reposotry.NewsRepositry

class NewsViewModelProviderFactory(private val newsRepositry: NewsRepositry) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(newsRepositry) as T
    }
}