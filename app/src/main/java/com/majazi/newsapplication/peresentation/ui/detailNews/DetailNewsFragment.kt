package com.majazi.newsapplication.peresentation.ui.detailNews

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.majazi.newsapplication.MainActivity
import com.majazi.newsapplication.R
import com.majazi.newsapplication.data.utils.Resource
import com.majazi.newsapplication.databinding.FragmentDetailNewsBinding
import com.majazi.newsapplication.databinding.FragmentListNewsBinding
import com.majazi.newsapplication.peresentation.adapter.NewsListAdapter
import com.majazi.newsapplication.peresentation.ui.ListNewsFragmentArgs
import com.majazi.newsapplication.peresentation.viewmodel.detailnews.DetailNewsViewModel
import com.majazi.newsapplication.peresentation.viewmodel.newslist.NewListViewModel


class DetailNewsFragment : Fragment() {
    private lateinit var binding: FragmentDetailNewsBinding
    private val args : DetailNewsFragmentArgs by navArgs()
    private lateinit var viewModel: DetailNewsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_detail_news, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getBundle()
        viewModel = (activity as MainActivity).detailNewsViewModel
        viewNewsList()

    }


    private fun getBundle() :String{
        val id = args.id
        return id.toString()
    }


    private fun viewNewsList() {
        viewModel.getDetailNews(getBundle())
        viewModel.news.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        setupWebView(it.content)
                        Glide.with(binding.imgHeaderNews.context)
                            .load(it.image.og_image)
                            .into(binding.imgHeaderNews)
                    }
                }

                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(activity, "Error : $it", Toast.LENGTH_LONG).show()
                        Log.i("TAG", "viewNewsList2q: $it")
                    }
                }

                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        }
    }

    private fun setupWebView(html:String) {
        binding.webViewDetail.settings.javaScriptEnabled = true
        binding.webViewDetail.loadData(html,"text/html; charset=utf-8", "UTF-8")
    }


    private fun showProgressBar(){
        binding.progressDetail.visibility = View.VISIBLE
    }

    private fun hideProgressBar(){
        binding.progressDetail.visibility = View.INVISIBLE
    }

}