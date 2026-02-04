package com.example.newsapp.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentArticleBinding
import com.example.newsapp.model.entity.Article
import com.example.newsapp.view.MainActivity
import com.example.newsapp.view.adapters.NewsAdapter
import com.example.newsapp.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleFragment : Fragment() {
    lateinit var binding: FragmentArticleBinding
    lateinit var newsAdapter: NewsAdapter
    lateinit var newsViewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArticleBinding.inflate(inflater,container,false)
        newsViewModel = (activity as MainActivity).newsViewModel
        newsAdapter = NewsAdapter()
        binding.article = arguments?.getParcelable<Article>("article")
        binding.newsViewModel = newsViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }
}