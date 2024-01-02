package com.majazi.newsapplication.peresentation.ui.listnews

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import com.bumptech.glide.Glide
import com.majazi.newsapplication.MainActivity
import com.majazi.newsapplication.R
import com.majazi.newsapplication.data.utils.SaveSharedP
import com.majazi.newsapplication.databinding.FragmentListNewsBinding
import com.majazi.newsapplication.peresentation.adapter.NewsListAdapter
import com.majazi.newsapplication.peresentation.viewmodel.newslist.NewListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ListNewsFragment : Fragment() {
    private lateinit var binding:FragmentListNewsBinding
    private val args : ListNewsFragmentArgs by navArgs()
    private  val viewModel: NewListViewModel by viewModels()
    private lateinit var newsAdapter: NewsListAdapter
    private var page = 1
    private var isScrolling = false
    private var isLoading = false
    private var isLastPage = false
    private var pages = 0

    private lateinit var explorerAdapter: PassengerListAdapter
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

        getAppIcon()
        getBundle()
//        viewModel = (activity as MainActivity).newsListViewModel
        newsAdapter = (activity as MainActivity).newsListAdapter

        viewNewsList()
        backPressed()

        newsAdapter.setOnItemClick {
            val bundle =Bundle().apply {
                putString("id",it.id.toString())
            }
            findNavController().navigate(
                R.id.action_listNewsFragment_to_detailNewsFragment,
                bundle
            )
        }

        newsAdapter.setOnSavedButtonClick {
            viewModel.saveNews(it)
        }

    }

    private fun backPressed() {
        binding.imbBackListNews.setOnClickListener {
            findNavController().popBackStack()
        }
    }


    private fun getBundle() :String{
        val id = args.postId
        return id.toString()
    }




    private fun viewNewsList() {
         viewModel.getNewsList(getBundle(),"2")

        explorerAdapter = PassengerListAdapter(requireContext())

        binding.recyListNews.adapter =
            explorerAdapter.withLoadStateFooter(footer = DefaultLoadStateAdapter { explorerAdapter.retry() })

        explorerAdapter.addLoadStateListener { combined ->

            binding.apply {
                pbSupportChannel.isVisible = combined.refresh is LoadState.Loading
                pbSupportChannel.isInvisible =
                    combined.refresh is LoadState.NotLoading || combined.refresh is LoadState.Error
            }

            binding.apply {
//                            refreshLayout.isRefreshing = false
//                            loadingBackground.visibility = View.GONE
                loadingPb.visibility = View.GONE
            }


        }


        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getNewsList(getBundle(),"5").collect { list ->
                explorerAdapter.submitData(list)


            }
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