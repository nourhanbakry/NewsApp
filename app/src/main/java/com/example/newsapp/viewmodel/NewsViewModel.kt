package com.example.newsapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.newsapp.utils.NewsState
import com.example.newsapp.model.entity.Article
import com.example.newsapp.model.reposotry.NewsRepositry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
   private val newsRepositry: NewsRepositry,
): ViewModel() {
    val breakingNewsLiveData = MutableLiveData<NewsState<List<Article?>?>>()
    var breakingNewsPage =1
    val searchNewsLiveData = MutableLiveData<NewsState<List<Article?>?>>()
    var searchNewsPage = 1
    val savedArticlesInDB = MutableLiveData<NewsState<List<Article?>?>>()
    val searchQuery = MutableStateFlow("")

     fun getBreakingNews(country: String){
         viewModelScope.launch {
             newsRepositry.getBreakingNews(country,breakingNewsPage).collect { newsState ->
                 breakingNewsLiveData.postValue(newsState)

             }

         }
     }


    fun searchForNews(searchQuery: String){
        viewModelScope.launch {
            newsRepositry.searchForNews(searchQuery,searchNewsPage).collect { newsState ->
                searchNewsLiveData.postValue(newsState)
            }
        }
    }


    fun upsertArticleInDB(article: Article){
        viewModelScope.launch {
            newsRepositry.upsertArticleInDB(article)
        }
    }

    fun deleteArticleFromDB(article: Article){
        viewModelScope.launch {
            newsRepositry.deleteArticleFromDB(article)
        }
    }

    fun getSavedArticlesFromDB(){
        viewModelScope.launch {
            newsRepositry.getSavedArticlesFromDB().collect { newsState ->
                savedArticlesInDB.postValue(newsState)
            }
        }
    }

    init {
        getBreakingNews("us")
        getSavedArticlesFromDB()
        viewModelScope.launch {
            searchQuery
                .debounce(500)
                .filter { it.isNotBlank() }
                .distinctUntilChanged()
                .collect { query ->
                    searchForNews(query)
                }
        }

    }
}