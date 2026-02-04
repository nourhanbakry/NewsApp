package com.example.newsapp.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.model.localdata.NewsDataBase
import com.example.newsapp.model.reposotry.NewsRepositry
import com.example.newsapp.viewmodel.NewsViewModel
import com.example.newsapp.viewmodel.NewsViewModelProviderFactory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val newsViewModel: NewsViewModel by viewModels()
    lateinit var newsRepositry: NewsRepositry
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // setup bottom nav bar with nav controller
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_frag) as NavHostFragment
        binding.bottomNavBar.setupWithNavController(navHostFragment.findNavController())
    }
}