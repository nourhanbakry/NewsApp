package com.example.newsapp.model.entity

import com.google.gson.annotations.SerializedName

data class NewsResponceModel(
    @SerializedName("articles")
    val articles: List<Article?>?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("totalResults")
    val totalResults: Int?
)