package com.example.newsapp.utils
import android.os.Bundle
import com.example.newsapp.R

import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.model.entity.Article
import com.example.newsapp.view.MainActivity
import com.example.newsapp.view.adapters.NewsAdapter
import com.example.newsapp.viewmodel.NewsViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, url: String?) {
    Glide.with(imageView.context)
        .load(url)
        .placeholder(R.drawable.no_image)
        .error(R.drawable.error_image)
        .into(imageView)
}

@BindingAdapter("moveTo")
fun moveTo(view: View, article: Article?) {
    if (article == null) return

    view.setOnClickListener {
        val navController = view.findNavController()
        val currentDestination = navController.currentDestination?.id

        val actionId = when (currentDestination) {
            R.id.brakingNewsFragment ->
                R.id.action_brakingNewsFragment_to_articleFragment

            R.id.searchNewsFragment ->
                R.id.action_searchNewsFragment_to_articleFragment

            R.id.savedNewsFragment ->
                R.id.action_savedNewsFragment_to_articleFragment

            else -> null
        }

        actionId?.let {
            navController.navigate(
                it,
                bundleOf("article" to article)
            )
        }
    }
}


@BindingAdapter(value = ["showWhenLoading"])
fun <T> showWhenLoading(view: View, state: NewsState<T>?){
    if(state is NewsState.Loading){
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}

@BindingAdapter(value = ["showWhenSuccess"])
fun <T> showWhenSuccess(view: View,state: NewsState<T>?){
    if(state is NewsState.Success){
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}


@BindingAdapter(value = ["showWhenEmpty"])
fun <T> showWhenEmpty(view: View,state: NewsState<T>?){
    if(state is NewsState.Empty){
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}

@BindingAdapter(value = ["showWhenFailed"])
fun <T> showWhenFailed(view: View,state: NewsState<T>?){
    if(state is NewsState.Failed){
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}



@BindingAdapter(value = ["setItems"])
fun <T> updateRecyclerViewItems(recyclerView: RecyclerView, items: List<Article?>?){
    val adapter = recyclerView.adapter as? NewsAdapter
    adapter?.submitList(items)
}


@BindingAdapter(value = ["loadArticle"])
fun loadArticle(webView: WebView, article: Article?) {
    article?.url?.let {
        webView.webViewClient = WebViewClient()
        webView.loadUrl(it)
    }
}



@BindingAdapter(value = ["fabClickArticle", "fabSavedList","newsViewModel"], requireAll = true)
fun fabClick(fab: FloatingActionButton, article: Article,savedArticles: List<Article?>?,newsViewModel: NewsViewModel) {
    val isSaved = savedArticles?.any { it?.url == article.url } == true
    fab.setImageResource(if (isSaved) R.drawable.filled_fav_icon else R.drawable.favs_icon )
    fab.setOnClickListener {
        if (isSaved) {
            newsViewModel.deleteArticleFromDB(article)
            Snackbar.make(fab, "Removed from saved", Snackbar.LENGTH_SHORT).show()
        } else {
            newsViewModel.upsertArticleInDB(article)
            Snackbar.make(fab, "Added to saved", Snackbar.LENGTH_SHORT).show()
        }
    }
}

