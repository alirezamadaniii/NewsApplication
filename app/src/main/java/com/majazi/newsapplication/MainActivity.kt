package com.majazi.newsapplication

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.majazi.newsapplication.databinding.ActivityMainBinding
import com.majazi.newsapplication.peresentation.adapter.CommentAdapter
import com.majazi.newsapplication.peresentation.adapter.DetailNewsAdapter
import com.majazi.newsapplication.peresentation.adapter.HomeNewsAdapter
import com.majazi.newsapplication.peresentation.adapter.NewsListAdapter
import com.majazi.newsapplication.peresentation.adapter.SavedNewsAdapter
import com.majazi.newsapplication.peresentation.adapter.SearchNewsAdapter
import com.majazi.newsapplication.peresentation.viewmodel.detailnews.DetailNewsFactory
import com.majazi.newsapplication.peresentation.viewmodel.detailnews.DetailNewsViewModel
import com.majazi.newsapplication.peresentation.viewmodel.home.NewsViewModel
import com.majazi.newsapplication.peresentation.viewmodel.home.NewsViewModelFactory
import com.majazi.newsapplication.peresentation.viewmodel.newslist.NewListViewModel
import com.majazi.newsapplication.peresentation.viewmodel.newslist.NewsListViewModelFactory
import com.majazi.newsapplication.peresentation.viewmodel.savenews.SaveNewsViewModel
import com.majazi.newsapplication.peresentation.viewmodel.savenews.SaveNewsViewModelFactory
import com.majazi.newsapplication.peresentation.viewmodel.search.SearchNewsViewModel
import com.majazi.newsapplication.peresentation.viewmodel.search.SearchNewsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: NewsViewModelFactory

    @Inject
    lateinit var factoryDetailNews:DetailNewsFactory
    @Inject
    lateinit var factorySearchNews: SearchNewsViewModelFactory
    @Inject
    lateinit var factorySaveNews:SaveNewsViewModelFactory

    lateinit var viewModel: NewsViewModel
    lateinit var detailNewsViewModel: DetailNewsViewModel
    lateinit var searchNewsViewModel: SearchNewsViewModel
    lateinit var saveNewsViewModel: SaveNewsViewModel


    @Inject
    lateinit var newsListAdapter: NewsListAdapter
    @Inject
    lateinit var newsAdapter: HomeNewsAdapter
    @Inject
    lateinit var detailNewsAdapter:DetailNewsAdapter
    @Inject
    lateinit var savedNewsAdapter: SavedNewsAdapter
    @Inject
    lateinit var searchNewsAdapter: SearchNewsAdapter
    @Inject
    lateinit var commentAdapter: CommentAdapter




    private lateinit var binding:ActivityMainBinding
    private lateinit var  navController:NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        initBottomNavigation()

        viewModel = ViewModelProvider(this,factory)[NewsViewModel::class.java]
        detailNewsViewModel = ViewModelProvider(this,factoryDetailNews)[DetailNewsViewModel::class.java]
        searchNewsViewModel = ViewModelProvider(this,factorySearchNews)[SearchNewsViewModel::class.java]
        saveNewsViewModel = ViewModelProvider(this,factorySaveNews)[SaveNewsViewModel::class.java]
    }



    private fun initBottomNavigation(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        navController = navHostFragment.navController
        val navView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        navView.setupWithNavController(navController)




        // delete bottom navigation
        navController.addOnDestinationChangedListener { _: NavController?, destination: NavDestination, _: Bundle? ->
            when (destination.id) {
                R.id.homeFragment -> {
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }
                R.id.loginFragment -> {
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }
                R.id.settingFragment -> {
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }
                R.id.savedNewsFragment -> {
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }
                else -> {
                    binding.bottomNavigationView.visibility = View.GONE
                }
            }
        }
    }

}