package com.example.newsapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.newsapp.databinding.FragmentBrakingNewsBinding
import com.example.newsapp.view.MainActivity
import com.example.newsapp.view.adapters.NewsAdapter
import com.example.newsapp.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BrakingNewsFragment : Fragment() {
    lateinit var newsViewModel: NewsViewModel
    lateinit var binding: FragmentBrakingNewsBinding
    lateinit var newsAdapter: NewsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBrakingNewsBinding.inflate(inflater,container,false)
        newsViewModel = (activity as MainActivity).newsViewModel
        newsAdapter = NewsAdapter()
        binding.newsViewModel = newsViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recyclerView.adapter = newsAdapter
        return binding.root
    }

}