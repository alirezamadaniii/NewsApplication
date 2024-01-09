package com.majazi.newsapplication.peresentation.ui.savedNews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.majazi.newsapplication.MainActivity
import com.majazi.newsapplication.R
import com.majazi.newsapplication.data.utils.SaveSharedP
import com.majazi.newsapplication.databinding.FragmentSavedNewsBinding
import com.majazi.newsapplication.peresentation.adapter.SavedNewsAdapter
import com.majazi.newsapplication.peresentation.viewmodel.savenews.SaveNewsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SavedNewsFragment : Fragment() {
    private lateinit var binding: FragmentSavedNewsBinding
    private lateinit var savedNewsAdapter: SavedNewsAdapter
    private val viewModelSaveNews: SaveNewsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_saved_news, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savedNewsAdapter = (activity as MainActivity).savedNewsAdapter
        getAppIcon()
        viewNewsList()
        removeNews()
    }


    private fun viewNewsList() {
        viewModelSaveNews.getSavedNews().observe(viewLifecycleOwner) { response ->
            if (response.isNotEmpty()){
                binding.recySavedNews.adapter = savedNewsAdapter
                savedNewsAdapter.differ.submitList(response)
            }else{
                binding.tvEmptyList.visibility = View.VISIBLE
                savedNewsAdapter.differ.submitList(response)
            }
        }
    }

    private fun removeNews(){
        savedNewsAdapter.setOnDeleteButtonClick {
            viewModelSaveNews.deleteNews(it)
        }

    }

    private fun getAppIcon(){
        val appIcon:String? = SaveSharedP.fetch(requireContext(),"app_icon")
        if (!appIcon.isNullOrEmpty()){
            Glide.with(binding.shapeableImageView.context)
                .load(appIcon)
                .into(binding.shapeableImageView)
        }
    }
}