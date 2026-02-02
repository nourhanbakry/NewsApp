package com.example.newsapp.model.remotedata


import com.example.newsapp.model.entity.NewsResponceModel
import com.example.newsapp.utils.API_KEY
import com.example.newsapp.utils.BREAKING_NEWS_EP
import com.example.newsapp.utils.NEWS_EP
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiMethods {

    @GET(BREAKING_NEWS_EP)
    suspend fun getBreakingNews(
        @Query("country")  country: String = "us",
        @Query("page") pageNumber: Int = 1,
        @Query("apiKey") apiKey:String = API_KEY
    ): Response<NewsResponceModel>



    @GET(NEWS_EP)
    suspend fun searchForNews(
        @Query("q")  searchQuery: String,
        @Query("page") pageNumber: Int = 1,
        @Query("apiKey") apiKey:String = API_KEY
    ): Response<NewsResponceModel>
}