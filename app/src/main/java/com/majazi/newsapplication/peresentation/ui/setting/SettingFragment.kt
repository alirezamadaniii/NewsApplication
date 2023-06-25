package com.majazi.newsapplication.peresentation.ui.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.majazi.newsapplication.R
import com.majazi.newsapplication.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {
    private lateinit var binding:FragmentSettingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_setting, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onclick()
    }

    private fun onclick() {
        binding.tvAboutUs.setOnClickListener{
            findNavController().navigate(R.id.action_settingFragment_to_aboutUsFragment)
        }

        binding.tvContactUs.setOnClickListener {
            findNavController().navigate(R.id.action_settingFragment_to_contactUsFragment)
        }
    }

}