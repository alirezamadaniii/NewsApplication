package com.majazi.newsapplication.peresentation.ui.detailNews

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.exoplayer2.Player
import com.majazi.newsapplication.MainActivity
import com.majazi.newsapplication.R
import com.majazi.newsapplication.data.model.detailnews.comment.Data
import com.majazi.newsapplication.data.model.detailnews.comment.SignInUser
import com.majazi.newsapplication.data.utils.Resource
import com.majazi.newsapplication.data.utils.dialog
import com.majazi.newsapplication.databinding.FragmentDetailNewsBinding
import com.majazi.newsapplication.peresentation.adapter.CommentAdapter
import com.majazi.newsapplication.peresentation.adapter.DetailNewsAdapter
import com.majazi.newsapplication.peresentation.viewmodel.detailnews.DetailNewsViewModel


class DetailNewsFragment : Fragment() {
    private lateinit var binding: FragmentDetailNewsBinding
    private val args: DetailNewsFragmentArgs by navArgs()
    private lateinit var viewModel: DetailNewsViewModel
    private lateinit var detailNewsAdapter: DetailNewsAdapter
    private lateinit var commentAdapter: CommentAdapter
    private lateinit var player: Player
    private var url = ""
    private var title = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_detail_news, container, false)
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
        backPressed()
        getUserData()
        shareButton()
    }

    private fun shareButton() {
        binding.imbShare.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            val shareBody = url
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_SUBJECT, title)
            intent.putExtra(Intent.EXTRA_TEXT, shareBody)
            startActivity(
                Intent.createChooser(
                    intent,
                    getString(R.string.hello_blank_fragment)
                )
            )
        }
    }


    private fun getUserData() {
        viewModel.getUser().observe(viewLifecycleOwner) { db ->

            binding.txtInputLayout.setEndIconOnClickListener { click ->
                //check be sing in user
                try {
                    if (db.username.isEmpty()) {
//                        val dialog =
//                            requireContext().dialog(R.layout.dialog_sign_in, requireView(), true)
//                        dialog.findViewById<Button>(R.id.btn_sign_in).setOnClickListener {
//                            val username =
//                                dialog.findViewById<EditText>(R.id.user_name).text.toString()
//                            val email = dialog.findViewById<EditText>(R.id.email).text.toString()
//                            if (username.isEmpty() || email.isEmpty()) {
//                                Toast.makeText(
//                                    activity,
//                                    "لطفا فیلدهای بالا را به دقت پر کنید",
//                                    Toast.LENGTH_LONG
//                                ).show()
//                            }
//                        }

                    } else {
                        viewModel.sendCommentNews(
                            binding.edtComment.text.toString(),
                            getBundle(),
                            db.username,
                            Settings.Secure.getString(getContext()?.getContentResolver(),
                                Settings.Secure.ANDROID_ID)
                        )
                        viewModel.sendComment.observe(viewLifecycleOwner) { response ->
                            when (response) {
                                is Resource.Success -> {
                                    Toast.makeText(activity, "okkk", Toast.LENGTH_LONG).show()
//                                    binding.imbSendComment.visibility = View.VISIBLE
//                                    binding.progressSendComment.visibility = View.GONE
                                    binding.edtComment.text?.clear()
//                                    val user = User("sdsad",1,"sdds","")
//                                    val data=Data(binding.edtComment.text.toString(),
//                                        "",
//                                        "",
//                                        1287,
//                                        getBundle().toInt(),
//                                        1,
//                                        user,
//                                        555871)
//                                    commentAdapter.addComment(data)
//                                    commentAdapter.addComment()
                                    showComment()

                                }

                                is Resource.Error -> {
//                                    binding.imbSendComment.visibility = View.VISIBLE
//                                    binding.progressSendComment.visibility = View.GONE
                                    binding.edtComment.text?.clear()
                                }

                                is Resource.Loading -> {
//                                    binding.imbSendComment.visibility = View.GONE
//                                    binding.progressSendComment.visibility = View.VISIBLE
                                }
                            }
                        }
                    }
                } catch (e: Exception) {
                    val dialog =
                        requireContext().dialog(R.layout.dialog_sign_in, requireView(), true)
                    dialog.findViewById<Button>(R.id.btn_sign_in).setOnClickListener {
                        var username =
                            dialog.findViewById<EditText>(R.id.user_name).text.toString()
                        var email = dialog.findViewById<EditText>(R.id.email).text.toString()
                        if (username.isEmpty() || email.isEmpty()) {
                            Toast.makeText(
                                activity,
                                "لطفا فیلدهای بالا را به دقت پر کنید",
                                Toast.LENGTH_LONG
                            ).show()
                        } else {
                            val signInUser = SignInUser(
                                username,
                                email
                            )
                            viewModel.signInUser(signInUser)
                            dialog.cancel()
                            dialog.dismiss()
                        }
                    }
                }
            }
        }
    }


    private fun backPressed() {
        binding.toolbarDetail.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }


    private fun getBundle(): String {
        val id = args.id
        return id.toString()
    }


    private fun viewNewsList() {
        detailNewsAdapter.setOnItemClick {
            player = it
        }

        Log.i("TAG", "viewNewsList: ${getBundle()}")
        viewModel.getDetailNews(getBundle())
        viewModel.news.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        setupWebView(it.content)
                        try {
                            url = it.short_link
                            title = it.title
                            Glide.with(binding.imgHeaderNews.context)
                                .load(it.image.og_image)
                                .into(binding.imgHeaderNews)
                        } catch (e: Exception) {
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

    private fun setupWebView(html: String) {
        binding.webViewDetail.settings.javaScriptEnabled = true
        //add font
        val pish =
            "<html><head><style type=\"text/css\">@font-face {font-family: MyFont;src: url(\"file:///android_asset/font/shabnam.ttf\")}body {direction : rtl;font-family: MyFont;font-size: medium;text-align: justify;}</style></head><body>"
        val pas = "</body></html>"
        val myHtmlString = pish + html + pas
        binding.webViewDetail.loadDataWithBaseURL(null, myHtmlString, "text/html", "UTF-8", null)
    }


    private fun showComment() {
        viewModel.getComment(getBundle())
        viewModel.comment.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let {
                        binding.recyComment.adapter = commentAdapter
                        commentAdapter.differ.submitList(it.data)
                    }
                }

                is Resource.Error -> {
//                    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(activity, "Error : $it", Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Loading -> {}
            }
        }
    }


    private fun showProgressBar() {
        binding.progressDetail.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressDetail.visibility = View.INVISIBLE
    }


    override fun onDestroy() {
        super.onDestroy()
        try {
            if (this::detailNewsAdapter.isInitialized) {
                detailNewsAdapter.release()
            }
        } catch (e: Exception) {
            Log.i("TAG", "onDestroy: ${e.message}")
        }
    }

}