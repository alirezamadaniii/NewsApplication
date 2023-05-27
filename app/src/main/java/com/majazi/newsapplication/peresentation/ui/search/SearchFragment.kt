package com.majazi.newsapplication.peresentation.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.example.global.utils.TextWatchers
import com.majazi.newsapplication.MainActivity
import com.majazi.newsapplication.R
import com.majazi.newsapplication.data.utils.Resource
import com.majazi.newsapplication.databinding.FragmentSearchBinding
import com.majazi.newsapplication.peresentation.viewmodel.search.SearchNewsViewModel

class SearchFragment : Fragment() {

    private lateinit var binding:FragmentSearchBinding
    private lateinit var viewModel:SearchNewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_search, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).searchNewsViewModel
//        binding.sasas.setOnClickListener {
            binding.edtSearch.addTextChangedListener(object :TextWatcher{
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }

                override fun afterTextChanged(s: Editable?) {
                    viewSearchNews(s.toString())
                }

            })

//        }

    }

    private fun viewSearchNews(search:String){
        viewModel.getNewsFromSearch(search)
        viewModel.news.observe(viewLifecycleOwner){ response ->
            when(response){
                is Resource.Success->{
                    hideProgressBar()
                    response.data?.let {
//                        binding.recyListNews.adapter = newsAdapter
//                        newsAdapter.differ.submitList(it.data.toList())
                        Log.i("TAG", "viewSearchNews: "+it.data[0].title.toString())
                    }
                }
                is Resource.Error ->{
                    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(activity, "Error : $it", Toast.LENGTH_LONG).show()
                        Log.i("TAG", "viewNewsList2q: $it")
                    }
                }
                is Resource.Loading->{
                    showProgressBar()
                }

            }
        }
    }


    private fun showProgressBar(){
        binding.progressSearch.visibility = View.VISIBLE
    }

    private fun hideProgressBar(){
        binding.progressSearch.visibility = View.INVISIBLE
    }
}