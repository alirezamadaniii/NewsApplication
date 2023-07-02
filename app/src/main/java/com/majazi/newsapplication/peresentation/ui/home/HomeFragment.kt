package com.majazi.newsapplication.peresentation.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.majazi.newsapplication.MainActivity
import com.majazi.newsapplication.R
import com.majazi.newsapplication.data.utils.Resource2
import com.majazi.newsapplication.data.utils.ResourceTrending
import com.majazi.newsapplication.databinding.FragmentHomeBinding
import com.majazi.newsapplication.peresentation.adapter.HomeNewsAdapter
import com.majazi.newsapplication.peresentation.ui.adapter.SpannedGridLayoutManager
import com.majazi.newsapplication.peresentation.viewmodel.home.NewsViewModel


class HomeFragment : Fragment() {

    private lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter: HomeNewsAdapter
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onclick()
        viewModel = (activity as MainActivity).viewModel
        newsAdapter = (activity as MainActivity).newsAdapter
        newsAdapter.setOnItemClick {
           val bundle =Bundle().apply {
               putString("post_id",it.id.toString())
           }
            findNavController().navigate(
                R.id.action_homeFragment_to_listNewsFragment,
                bundle
            )
        }
        initRecyclerView()
        viewNewsList()
        trendingNews()

    }

    @SuppressLint("SetTextI18n")
    private fun trendingNews() {
        viewModel.getTrendingNews()
        viewModel.trendingNews.observe(viewLifecycleOwner){response->
            when (response) {
                is ResourceTrending.Success -> {

                    response.data.let {
                                        Glide.with(binding.shapeableImageView.context)
                                            .load(R.drawable.home_app_icon)
                                            .into(binding.shapeableImageView)
                        binding.materialTextView.isSelected = true
                        var trendingNews=""
                        it.forEach { news->
                            trendingNews += news.title+"       "
                        }
                        binding.materialTextView.text = trendingNews

                    }
                }

                is ResourceTrending.Error -> {
                    response.message?.let {
                        Toast.makeText(activity, "Error : $it", Toast.LENGTH_LONG).show()
                    }
                }

                is ResourceTrending.Loading -> {
                }
            }
        }
    }

    private fun viewNewsList() {
       viewModel.getNews()
        viewModel.news.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource2.Success -> {
                    hideProgressBar()
                    response.data.let {
                        newsAdapter.differ.submitList(it)
                    }
                }

                is Resource2.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(activity, "Error : $it", Toast.LENGTH_LONG).show()
                    }
                }

                is Resource2.Loading -> {
                    showProgressBar()
                }
            }
        }
    }


    private fun initRecyclerView() {
        setupSpannedGridLayout()
        binding.recyHome.adapter = newsAdapter
    }


    private fun showProgressBar(){
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar(){
        binding.progressBar.visibility = View.INVISIBLE
    }

    private fun onclick() {
        binding.imbSearch.setOnClickListener {
            Navigation.findNavController(it).
                    navigate(R.id.action_homeFragment_to_searchFragment)
        }
    }


    private fun setupSpannedGridLayout() {
        val manager = SpannedGridLayoutManager(
            object : SpannedGridLayoutManager.GridSpanLookup {
                override fun getSpanInfo(position: Int): SpannedGridLayoutManager.SpanInfo {
                    return if (position % 12 == 0 || position % 12 == 7) {
                        SpannedGridLayoutManager.SpanInfo(2, 2)
                    } else {
                        SpannedGridLayoutManager.SpanInfo(1, 1)
                    }
                }
            },
            3,
            1f
        )
        binding.recyHome.layoutManager =manager
    }

}