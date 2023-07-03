package com.majazi.newsapplication.peresentation.ui.listnews

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.majazi.newsapplication.MainActivity
import com.majazi.newsapplication.R
import com.majazi.newsapplication.data.utils.Resource
import com.majazi.newsapplication.data.utils.ResourceListNews
import com.majazi.newsapplication.databinding.FragmentListNewsBinding
import com.majazi.newsapplication.peresentation.adapter.NewsListAdapter
import com.majazi.newsapplication.peresentation.viewmodel.newslist.NewListViewModel


class ListNewsFragment : Fragment() {
    private lateinit var binding:FragmentListNewsBinding
    private val args : ListNewsFragmentArgs by navArgs()
    private lateinit var viewModel: NewListViewModel
    private lateinit var newsAdapter: NewsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_list_news, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getBundle()

        viewModel = (activity as MainActivity).newsListViewModel
        newsAdapter = (activity as MainActivity).newsListAdapter

        viewNewsList()

        newsAdapter.setOnItemClick {
            val bundle =Bundle().apply {
                putString("id",it.id.toString())
            }
            findNavController().navigate(
                R.id.action_listNewsFragment_to_detailNewsFragment,
                bundle
            )
        }

//        newsAdapter.setOnSavedButtonClick {
//            viewModel.saveNews(it)
//        }


    }


    private fun getBundle() :String{
        val id = args.postId
        return id.toString()
    }




    private fun viewNewsList() {
        viewModel.getNewsList(getBundle())
        viewModel.newsList.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ResourceListNews.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        binding.recyListNews.adapter = newsAdapter
                        newsAdapter.differ.submitList(it)
                    }
                }

                is ResourceListNews.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(activity, "Error : $it", Toast.LENGTH_LONG).show()
                    }
                }

                is ResourceListNews.Loading -> {
                    showProgressBar()
                }
            }
        }
        viewModel.isInternetAvailable.observe(viewLifecycleOwner){
            Toast.makeText(activity, "$it", Toast.LENGTH_LONG).show()
        }
    }




    private fun showProgressBar(){
        binding.progressNewsList.visibility = View.VISIBLE
    }

    private fun hideProgressBar(){
        binding.progressNewsList.visibility = View.INVISIBLE
    }

}