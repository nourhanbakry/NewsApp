package com.example.newsapp.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newsapp.view.MainActivity
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentSearchNewsBinding
import com.example.newsapp.view.adapters.NewsAdapter
import com.example.newsapp.viewmodel.NewsViewModel

class SearchNewsFragment : Fragment() {
    lateinit var newsViewModel: NewsViewModel
    lateinit var binding: FragmentSearchNewsBinding
    lateinit var newsAdapter: NewsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchNewsBinding.inflate(inflater,container,false)
        newsViewModel = (activity as MainActivity).newsViewModel
        newsAdapter = NewsAdapter()
        binding.newsViewModel = newsViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.rvSearchNews.adapter = newsAdapter
        return binding.root
    }


}