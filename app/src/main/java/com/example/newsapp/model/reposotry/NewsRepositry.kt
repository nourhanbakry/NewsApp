package com.example.newsapp.model.reposotry

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.TYPE_ETHERNET
import android.net.ConnectivityManager.TYPE_WIFI
import android.net.NetworkCapabilities.TRANSPORT_CELLULAR
import android.net.NetworkCapabilities.TRANSPORT_ETHERNET
import android.net.NetworkCapabilities.TRANSPORT_WIFI
import android.os.Build
import android.provider.ContactsContract.CommonDataKinds.Email.TYPE_MOBILE
import com.example.newsapp.utils.NewsState
import com.example.newsapp.model.entity.Article
import com.example.newsapp.model.localdata.NewsDataBase
import com.example.newsapp.model.remotedata.SetupRetrofit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepositry @Inject constructor(private val newsDataBase: NewsDataBase,private val application: Application) {

    fun getBreakingNews(country:String, pageNo:Int): Flow<NewsState<List<Article?>?>> {
        return flow {
            emit(NewsState.Loading)
            try {
                if (isUserConnectedToInternet()){
                    val result = SetupRetrofit.retrofitObject.getBreakingNews(
                        country = country,
                        pageNumber = pageNo
                    )
                    if (result.isSuccessful) {
                        emit(NewsState.Success(result.body()?.articles))

                    } else {
                        emit(NewsState.Failed("Error while user connected is ${result.message()}"))
                    }
                }else {
                    emit(NewsState.Failed("No Internet Connection"))

                }

            } catch (e: Exception) {
                emit(NewsState.Failed(e.message ?: "Excpetion is $e"))
            }

        }
    }

    fun searchForNews(searchQuery:String, pageNo:Int): Flow<NewsState<List<Article?>?>> {
        return flow {
            emit(NewsState.Loading)
            try {
                if (isUserConnectedToInternet()){
                    val result = SetupRetrofit.retrofitObject.searchForNews(
                        searchQuery = searchQuery,
                        pageNumber = pageNo
                    )
                    if (result.isSuccessful) {
                        emit(NewsState.Success(result.body()?.articles))

                    } else {
                        emit(NewsState.Failed("Error while user connected is ${result.message()}"))
                    }
                } else{
                    emit(NewsState.Failed("No Internet Connection"))
                }

            } catch (e: Exception) {
                emit(NewsState.Failed(e.message ?: "Excpetion is $e"))
            }

        }
    }

   suspend fun upsertArticleInDB(article: Article){
       newsDataBase.newsDAO().upsertNews(article)
   }

    suspend fun deleteArticleFromDB(article: Article){
        newsDataBase.newsDAO().deleteArticle(article)
    }

    fun getSavedArticlesFromDB(): Flow<NewsState<List<Article>>> {
        return newsDataBase.newsDAO().getAllArticles().map { articlesList ->
            if (articlesList.isEmpty()) NewsState.Empty
            else NewsState.Success(articlesList)
        }.catch {
                e -> emit(NewsState.Failed(e.message ?: "Exception is $e"))
        }
    }

    fun isUserConnectedToInternet(): Boolean{
        val connectivityManager = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when{
                capabilities.hasTransport(TRANSPORT_WIFI) ->true
                capabilities.hasTransport(TRANSPORT_CELLULAR) ->true
                capabilities.hasTransport(TRANSPORT_ETHERNET) ->true
                else -> false
            }
        }else{
            connectivityManager.activeNetworkInfo.run {
                return when(this?.type){
                         TYPE_WIFI -> true
                         TYPE_MOBILE ->true
                         TYPE_ETHERNET ->  true
                         else -> false
                }
            }
        }
    }

}