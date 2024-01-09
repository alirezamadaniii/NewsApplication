package com.majazi.newsapplication.peresentation.ui.search
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.majazi.newsapplication.MainActivity
import com.majazi.newsapplication.R
import com.majazi.newsapplication.data.utils.Resource
import com.majazi.newsapplication.databinding.FragmentSearchBinding
import com.majazi.newsapplication.peresentation.adapter.SearchNewsAdapter
import com.majazi.newsapplication.peresentation.viewmodel.search.SearchNewsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding:FragmentSearchBinding
    private val viewModel:SearchNewsViewModel by viewModels()
    private lateinit var adapter: SearchNewsAdapter

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
        adapter = (activity as MainActivity).searchNewsAdapter
        backPressed()
        voiceSearch()

            binding.edtSearch.addTextChangedListener(object :TextWatcher{
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                        viewSearchNews(s.toString())
                }
            })


    }

    // Call Activity for Voice Input
    private fun voiceSearch() {
        binding.voiceSearch.setOnClickListener {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "fa")
            try {
                startActivityForResult(intent, 1)
            } catch (a: ActivityNotFoundException) {
                Toast.makeText(
                    context,
                    "گوشی شما تایپ صوتی را پشتیبانی نمیکند",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    // When Mic activity close
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1 -> {
                if (resultCode == Activity.RESULT_OK && null != data) {
                    val yourResult =
                        data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)!![0]
                        binding.edtSearch.setText(yourResult)
                }
            }
        }
    }

    private fun backPressed() {
        binding.imbBackSearch.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun viewSearchNews(search:String){
        viewModel.getNewsFromSearch(search)
        viewModel.news.observe(viewLifecycleOwner){ response ->
            when(response){
                is Resource.Success->{
                    hideProgressBar()
                    response.data?.let {
                        binding.recyclerView.adapter = adapter
                        adapter.differ.submitList(it.data.toList())

                    }
                }
                is Resource.Error ->{
                    hideProgressBar()
                    response.message?.let {
                        if (it != "Unprocessable Content"){
                            Toast.makeText(activity, "Error : $it", Toast.LENGTH_LONG).show()
                        }

                    }
                }
                is Resource.Loading->{
                    showProgressBar()
                }

            }
        }

        adapter.setOnItemClick {
            val bundle =Bundle().apply {
                putString("id",it.id.toString())
            }
            findNavController().navigate(
                R.id.action_searchFragment_to_detailNewsFragment,
                bundle
            )
        }
    }


    private fun showProgressBar(){
        binding.progressSearch.visibility = View.VISIBLE
    }

    private fun hideProgressBar(){
        binding.progressSearch.visibility = View.INVISIBLE
    }
}