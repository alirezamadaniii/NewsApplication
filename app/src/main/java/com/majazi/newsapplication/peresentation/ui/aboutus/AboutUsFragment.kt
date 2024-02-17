package com.majazi.newsapplication.peresentation.ui.aboutus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.majazi.newsapplication.R
import com.majazi.newsapplication.databinding.FragmentAboutUsBinding

class AboutUsFragment : Fragment() {
    private lateinit var binding:FragmentAboutUsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_about_us, container, false)
        return binding.root
    }


}