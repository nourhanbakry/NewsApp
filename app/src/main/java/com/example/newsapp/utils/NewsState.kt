package com.example.newsapp.utils

sealed class NewsState<out T> {
    object Loading : NewsState<Nothing>()
    data class Failed(val message: String) :NewsState<Nothing>()
    data class Success<T>(val news:T) : NewsState<T>()
    object Empty :NewsState<Nothing>()

    fun toData():T? = if(this is Success) news  else null
}