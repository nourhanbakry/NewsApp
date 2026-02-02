package com.example.newsapp.model.remotedata

import com.example.newsapp.utils.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SetupRetrofit {
    companion object{
        private  val retrofit: Retrofit by lazy{
            val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

        }

        val retrofitObject: NewsApiMethods by lazy {
            retrofit.create(NewsApiMethods::class.java)
        }
    }

}