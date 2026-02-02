package com.example.newsapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.databinding.ItemArticlePreviewBinding
import com.example.newsapp.model.entity.Article

class NewsAdapter : ListAdapter<Article, NewsAdapter.NewsViewHolder>(differCallBack) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_article_preview,parent,false)
       return NewsViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: NewsViewHolder,
        position: Int
    ) {
       val currentItem = getItem(position)
        currentItem?.let { article ->
            holder.articleItemBinding.itemArticle = article
        }
    }

    override fun getItemCount(): Int  = currentList.size

    class NewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val articleItemBinding = ItemArticlePreviewBinding.bind(itemView)
    }
     companion object{
         val differCallBack = object : DiffUtil.ItemCallback<Article>(){
             override fun areItemsTheSame(
                 oldItem: Article,
                 newItem: Article
             ): Boolean {
                 return  oldItem.url == newItem.url
             }

             override fun areContentsTheSame(
                 oldItem: Article,
                 newItem: Article
             ): Boolean {
                 return oldItem == newItem
             }

         }

     }

}