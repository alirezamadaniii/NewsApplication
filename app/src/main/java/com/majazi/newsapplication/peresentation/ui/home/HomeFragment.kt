package com.majazi.newsapplication.peresentation.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
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
import com.majazi.newsapplication.data.utils.Resource
import com.majazi.newsapplication.data.utils.ResourceItemNews
import com.majazi.newsapplication.data.utils.ResourceTrending
import com.majazi.newsapplication.data.utils.SaveSharedP
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
        getAppIcon()
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
                        var textSize:String? = SaveSharedP.fetch(requireContext(),"size_text")
                        if (textSize.equals("")){
                            textSize = "12"
                        }
                        binding.materialTextView.textSize = textSize?.toFloat()!!
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
                        Log.i("TAG", "trendingNews: $it")
                    }
                }

                is ResourceTrending.Loading -> {}
            }
        }
    }

    private fun viewNewsList() {
       viewModel.getNews()
        viewModel.news.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ResourceItemNews.Success -> {
                    hideProgressBar()
                    response.data.let {
                        newsAdapter.differ.submitList(it)
                    }
                }

                is ResourceItemNews.Error -> {
                    response.message?.let {
                        if (it.equals("lateinit property categoryList has not been initialized")){
                            Toast.makeText(activity,
                                "مشکل در ارتباط با سرور",
                                Toast.LENGTH_LONG).show()
                        }
                    }
                }

                is ResourceItemNews.Loading -> {
                    showProgressBar()
                }
            }
        }

        viewModel. isInternetAvailable.observe(viewLifecycleOwner){
            if (!it){
                Toast.makeText(activity,
                    "کاربر گرامی شما به اینترنت متصل نیستید",
                    Toast.LENGTH_LONG).show()
            }
        }
    }


    private fun getAppIcon(){
        val appICon:String? = SaveSharedP.fetch(requireContext(),"app_icon")
        if (!appICon.isNullOrEmpty()){
            Glide.with(binding.shapeableImageView.context)
                .load(appICon)
                .into(binding.shapeableImageView)
            Log.i("TAG", "getAppIcon: $appICon")
        }
        viewModel.getAppIcon()
        viewModel.appIcon.observe(viewLifecycleOwner){ response->
            when (response) {
                is Resource.Success -> {
                    response.data.let {
                        SaveSharedP.data(requireContext(),"app_icon",it?.data?.appIcon)
                        Glide.with(binding.shapeableImageView.context)
                            .load(it?.data?.appIcon)
                            .into(binding.shapeableImageView)
                    }
                }

                is Resource.Error -> {
                    response.message?.let {
                        if (it.equals("lateinit property categoryList has not been initialized")){
                            Toast.makeText(activity,
                                "مشکل در ارتباط با سرور",
                                Toast.LENGTH_LONG).show()
                        }
                    }
                }

                is Resource.Loading -> {}
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
            3 ,
            1f
        )
        binding.recyHome.layoutManager =manager
    }

}