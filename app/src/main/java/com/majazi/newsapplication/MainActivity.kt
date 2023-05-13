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
import com.majazi.newsapplication.peresentation.adapter.DetailNewsAdapter
import com.majazi.newsapplication.peresentation.adapter.HomeNewsAdapter
import com.majazi.newsapplication.peresentation.adapter.NewsListAdapter
import com.majazi.newsapplication.peresentation.viewmodel.detailnews.DetailNewsFactory
import com.majazi.newsapplication.peresentation.viewmodel.detailnews.DetailNewsViewModel
import com.majazi.newsapplication.peresentation.viewmodel.home.NewsViewModel
import com.majazi.newsapplication.peresentation.viewmodel.home.NewsViewModelFactory
import com.majazi.newsapplication.peresentation.viewmodel.newslist.NewListViewModel
import com.majazi.newsapplication.peresentation.viewmodel.newslist.NewsListViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: NewsViewModelFactory
    @Inject
    lateinit var factoryNewsList:NewsListViewModelFactory
    @Inject
    lateinit var factoryDetailNews:DetailNewsFactory


    lateinit var viewModel: NewsViewModel
    lateinit var newsListViewModel: NewListViewModel
    lateinit var detailNewsViewModel: DetailNewsViewModel


    @Inject
    lateinit var newsListAdapter: NewsListAdapter
    @Inject
    lateinit var newsAdapter: HomeNewsAdapter
    @Inject
    lateinit var detailNewsAdapter:DetailNewsAdapter



    private lateinit var binding:ActivityMainBinding
    private lateinit var  navController:NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        initBottomNavigation()

        viewModel = ViewModelProvider(this,factory)[NewsViewModel::class.java]
        newsListViewModel = ViewModelProvider(this,factoryNewsList)[NewListViewModel::class.java]
        detailNewsViewModel = ViewModelProvider(this,factoryDetailNews)[DetailNewsViewModel::class.java]
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
                R.id.profileFragment -> {
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