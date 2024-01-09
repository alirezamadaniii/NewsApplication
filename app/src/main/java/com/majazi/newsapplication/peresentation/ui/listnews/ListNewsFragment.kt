package com.majazi.newsapplication.peresentation.ui.listnews

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import com.bumptech.glide.Glide
import com.majazi.newsapplication.R
import com.majazi.newsapplication.data.model.Category
import com.majazi.newsapplication.data.utils.SaveSharedP
import com.majazi.newsapplication.databinding.FragmentListNewsBinding
import com.majazi.newsapplication.peresentation.viewmodel.home.NewsViewModel
import com.majazi.newsapplication.peresentation.viewmodel.newslist.NewListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ListNewsFragment : Fragment() {
    private lateinit var binding:FragmentListNewsBinding
    private val args : ListNewsFragmentArgs by navArgs()
    private val viewModel: NewListViewModel by viewModels()
    private val homeViewModel: NewsViewModel by viewModels()
    private lateinit var explorerAdapter: PassengerListAdapter
    private var number:Int = 1
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
        addCounter()
        viewNewsList()
        backPressed()


        explorerAdapter.setOnItemClick {
            val bundle =Bundle().apply {
                putString("id",it.id.toString())
            }
            findNavController().navigate(
                R.id.action_listNewsFragment_to_detailNewsFragment,
                bundle
            )
            homeViewModel.addCounter(Category(getBundle().toInt(),number))
        }

        explorerAdapter.setOnSavedButtonClick {
            viewModel.saveNews(it)
        }
    }

    private fun addCounter() {
        homeViewModel.getCounter(getBundle().toInt()).observe(viewLifecycleOwner){ot->
            number = ot+1
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
        explorerAdapter = PassengerListAdapter()
        binding.recyListNews.adapter =
            explorerAdapter.withLoadStateFooter(footer = DefaultLoadStateAdapter { explorerAdapter.retry() })
        explorerAdapter.addLoadStateListener { combined ->
            binding.apply {
                pbSupportChannel.isVisible = combined.refresh is LoadState.Loading
                pbSupportChannel.isInvisible =
                    combined.refresh is LoadState.NotLoading || combined.refresh is LoadState.Error
            }
            binding.apply {
                loadingPb.visibility = View.GONE
            }
        }
        explorerAdapter.setOnItemClick {

        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getNewsList(getBundle(),"10").collect { list ->
                explorerAdapter.submitData(list)
            }
        }

        viewModel.isInternetAvailable.observe(viewLifecycleOwner){
            if (!it){
                Toast.makeText(activity,
                    "کاربر گرامی شما به اینترنت متصل نیستید",
                    Toast.LENGTH_LONG).show()
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