package com.majazi.newsapplication.peresentation.ui.detailNews

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.exoplayer2.Player
import com.majazi.newsapplication.MainActivity
import com.majazi.newsapplication.R
import com.majazi.newsapplication.data.utils.Resource
import com.majazi.newsapplication.data.utils.Resource2
import com.majazi.newsapplication.databinding.FragmentDetailNewsBinding
import com.majazi.newsapplication.peresentation.adapter.CommentAdapter
import com.majazi.newsapplication.peresentation.adapter.DetailNewsAdapter
import com.majazi.newsapplication.peresentation.viewmodel.detailnews.DetailNewsViewModel


class DetailNewsFragment : Fragment() {
    private lateinit var binding: FragmentDetailNewsBinding
    private val args : DetailNewsFragmentArgs by navArgs()
    private lateinit var viewModel: DetailNewsViewModel
    private lateinit var detailNewsAdapter: DetailNewsAdapter
    private lateinit var commentAdapter: CommentAdapter
    private lateinit var player: Player

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_detail_news, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getBundle()
        viewModel = (activity as MainActivity).detailNewsViewModel
        detailNewsAdapter = (activity as MainActivity).detailNewsAdapter
        commentAdapter = (activity as MainActivity).commentAdapter
        viewNewsList()
        showComment()
        backPreesed()

    }

    private fun backPreesed() {
        binding.toolbarDetail.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }


    private fun getBundle() :String{
        val id = args.id
        return id.toString()
    }


    private fun viewNewsList() {


        detailNewsAdapter.setOnItemClick {
            player =it
        }

        viewModel.getDetailNews(getBundle())
        viewModel.news.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        setupWebView(it.content)
                        try {
                            Glide.with(binding.imgHeaderNews.context)
                                .load(it.image.og_image)
                                .into(binding.imgHeaderNews)
                        }catch (e:Exception){
                            Log.i("TAG", "viewNewsList: ${e.message}")
                        }

                            binding.recyDetail.adapter = detailNewsAdapter
                        detailNewsAdapter.differ.submitList(it.additional_contents)
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

        //add font
        val pish =
            "<html><head><style type=\"text/css\">@font-face {font-family: MyFont;src: url(\"file:///android_asset/font/shabnam.ttf\")}body {direction : rtl;font-family: MyFont;font-size: medium;text-align: justify;}</style></head><body>"
        val pas = "</body></html>"
        val myHtmlString = pish + html + pas
        binding.webViewDetail.loadDataWithBaseURL(null, myHtmlString, "text/html", "UTF-8", null)
    }


    private fun showComment(){
        viewModel.getComment(getBundle())
        viewModel.comment.observe(viewLifecycleOwner){ response->
            when(response){
                is Resource.Success->{
                    response.data?.let {

                        binding.recyComment.adapter = commentAdapter
                        commentAdapter.differ.submitList(it.data.toList())

                    }

                }
                is Resource.Error -> {
//                    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(activity, "Error : $it", Toast.LENGTH_LONG).show()
                        Log.i("TAG", "viewNewsList2q: $it")
                    }
                }

                is Resource.Loading -> {
//                    showProgressBar()
                }
            }
        }
    }


    private fun showProgressBar(){
        binding.progressDetail.visibility = View.VISIBLE
    }

    private fun hideProgressBar(){
        binding.progressDetail.visibility = View.INVISIBLE
    }


    override fun onDestroy() {
        super.onDestroy()
        try {
            if (this::detailNewsAdapter.isInitialized){
                detailNewsAdapter.release()
            }
        }catch (e:Exception){

        }

    }

}