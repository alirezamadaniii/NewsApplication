package com.majazi.newsapplication.peresentation.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.majazi.newsapplication.MainActivity
import com.majazi.newsapplication.R
import com.majazi.newsapplication.data.utils.Resource
import com.majazi.newsapplication.data.utils.ResourceItemNews
import com.majazi.newsapplication.data.utils.ResourceTrending
import com.majazi.newsapplication.data.utils.SaveSharedP
import com.majazi.newsapplication.databinding.FragmentHomeBinding
import com.majazi.newsapplication.peresentation.adapter.HomeNewsAdapter
import com.majazi.newsapplication.peresentation.adapter.SuggestionNewsAdapter
import com.majazi.newsapplication.peresentation.viewmodel.home.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: NewsViewModel by viewModels()
    private lateinit var newsAdapter: HomeNewsAdapter
    private lateinit var suggestionNewsAdapter: SuggestionNewsAdapter
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onclick()
        newsAdapter = (activity as MainActivity).newsAdapter
        newsAdapter.setOnItemClick {

            val bundle = Bundle().apply {
                putString("post_id", it.id.toString())
            }
            findNavController().navigate(
                R.id.action_homeFragment_to_listNewsFragment,
                bundle
            )
        }
        getAppIcon()
        initRecyclerView()

        lifecycleScope.launch {
            val categoryList = async { viewNewsList() }
            val interestPost = async { getInterestPost() }
            val trendingNews = async { trendingNews() }
            val userAuth = async { userAuth() }
            categoryList.await()
            interestPost.await()
            trendingNews.await()
            userAuth.await()
        }


    }

    private fun getInterestPost() {
        val category1 = JSONObject()
        val category2 = JSONObject()
        val category3 = JSONObject()
        viewModel.getAllCounter().observe(viewLifecycleOwner) { response ->
            try {
                category1.put("category_id", response[0].category_id)
                category1.put("count", 10)

            } catch (e: JSONException) {
                Toast.makeText(requireContext(), "عملیات با خطا مواجه شد", Toast.LENGTH_LONG).show()
                e.printStackTrace()
            }

            try {
                category2.put("category_id", response[1].category_id)
                category2.put("count", 7)

            } catch (e: JSONException) {
                Toast.makeText(requireContext(), "عملیات با خطا مواجه شد", Toast.LENGTH_LONG).show()
                e.printStackTrace()
            }


            try {
                category3.put("category_id", response[2].category_id)
                category3.put("count", 3)

            } catch (e: JSONException) {
                Toast.makeText(requireContext(), "عملیات با خطا مواجه شد", Toast.LENGTH_LONG).show()
                e.printStackTrace()
            }

            val jsonArray = JSONArray()
            jsonArray.put(category1)
            jsonArray.put(category2)
            jsonArray.put(category3)
            val studentsObj = JSONObject()
            studentsObj.put("category", jsonArray)

            val jsonStr = studentsObj.toString()
            val androidId = systemID()
            viewModel.getInterestPost(androidId, jsonStr)
            viewModel.interestPost.observe(viewLifecycleOwner) { responses ->
                when (responses) {
                    is Resource.Success -> {
                        responses.data.let {
                            binding.tvSuggestion.visibility = View.VISIBLE
                            suggestionNewsAdapter = SuggestionNewsAdapter()
                            binding.recySuggestion.adapter = suggestionNewsAdapter
                            suggestionNewsAdapter.differ.submitList(it?.data)

                            suggestionNewsAdapter.setOnItemClick {
                                val bundle = Bundle().apply {
                                    putString("id", it.id.toString())
                                }
                                findNavController().navigate(
                                    R.id.action_homeFragment_to_detailNewsFragment,
                                    bundle
                                )
                            }
                        }
                    }

                    is Resource.Error -> {
                    }

                    is Resource.Loading -> {}
                }
            }
        }


    }

    private fun userAuth() {
        val userAuth = SaveSharedP.fetch(requireContext(), "user_auth")
        if (userAuth.isNullOrEmpty()) {
            val androidId = systemID()
            viewModel.userAuth("android", androidId)
            viewModel.userAuthUseCase.observe(viewLifecycleOwner) { response ->
                when (response) {
                    is Resource.Success -> {
                        response.data.let {
                            SaveSharedP.data(
                                requireContext(), "user_auth",
                                it?.message?.userId.toString()
                            )
                        }
                    }

                    is Resource.Error -> {}

                    is Resource.Loading -> {}
                }
            }
        }

    }

    @SuppressLint("SetTextI18n")
    private fun trendingNews() {
        viewModel.getTrendingNews()
        viewModel.trendingNews.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ResourceTrending.Success -> {
                    response.data.let {
                        var textSize: String? = SaveSharedP.fetch(requireContext(), "size_text")
                        if (textSize.equals("")) {
                            textSize = "12"
                        }
                        binding.materialTextView.textSize = textSize?.toFloat()!!
                        binding.materialTextView.isSelected = true
                        var trendingNews = ""
                        it.forEach { news ->
                            trendingNews += news.title + "       "
                        }
                        binding.materialTextView.text = trendingNews

                    }
                }

                is ResourceTrending.Error -> {}

                is ResourceTrending.Loading -> {}
            }
        }
    }

    private fun viewNewsList() {
        viewModel.getNews()
        viewModel.news.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ResourceItemNews.Success -> {
                    response.data.let {
                        newsAdapter.differ.submitList(it)
                    }
                    binding.progressBar.visibility = View.GONE
                }

                is ResourceItemNews.Error -> {
                    response.message?.let {
                        if (it == "lateinit property categoryList has not been initialized") {
                            Toast.makeText(
                                activity,
                                "مشکل در ارتباط با سرور",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }

                is ResourceItemNews.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }

        viewModel.isInternetAvailable.observe(viewLifecycleOwner) {
            if (!it) {
                Toast.makeText(
                    activity,
                    "کاربر گرامی شما به اینترنت متصل نیستید",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }


    private fun getAppIcon() {
        val appICon: String? = SaveSharedP.fetch(requireContext(), "app_icon")
        if (!appICon.isNullOrEmpty()) {
            Glide.with(binding.shapeableImageView.context)
                .load(appICon)
                .into(binding.shapeableImageView)
        }
        viewModel.getAppIcon()
        viewModel.appIcon.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    response.data.let {
                        SaveSharedP.data(requireContext(), "app_icon", it?.data?.appIcon)
                        Glide.with(binding.shapeableImageView.context)
                            .load(it?.data?.appIcon)
                            .into(binding.shapeableImageView)
                    }
                }

                is Resource.Error -> {
                    response.message?.let {
                        if (it == "lateinit property categoryList has not been initialized") {
                            Toast.makeText(
                                activity,
                                "مشکل در ارتباط با سرور",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }

                is Resource.Loading -> {}
            }
        }
    }

    private fun initRecyclerView() {
        binding.recyHome.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.recyHome.adapter = newsAdapter
    }


    private fun onclick() {
        binding.imbSearch.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_searchFragment)
        }
    }

    private fun systemID(): String {
        return Settings.Secure.getString(
            context?.contentResolver,
            Settings.Secure.ANDROID_ID
        )
    }


}