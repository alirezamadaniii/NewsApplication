package com.majazi.newsapplication.peresentation.ui.setting

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.SeekBar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.majazi.newsapplication.R
import com.majazi.newsapplication.data.utils.SaveSharedP
import com.majazi.newsapplication.databinding.FragmentSettingBinding


class SettingFragment : Fragment() {
    private lateinit var binding: FragmentSettingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_setting, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setSizeText()
        onclick()
    }

    private fun onclick() {
        binding.lilAboutUs.setOnClickListener {
            findNavController().navigate(R.id.action_settingFragment_to_aboutUsFragment)
        }

        binding.lilContactUs.setOnClickListener {
            findNavController().navigate(R.id.action_settingFragment_to_contactUsFragment)
        }
    }

    private fun animText(){
        val animation1 = AlphaAnimation(0.2f, 1.0f)
        animation1.duration = 1000
    }
    private fun setSizeText() {
        var textSize: String? = SaveSharedP.fetch(requireContext(), "size_text")
        binding.seekbarTextSize.progress = (textSize?.toInt()?.minus(16))!!
        if (textSize.equals("")) {
            textSize = "0"
        }



        binding.seekbarTextSize.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                textSize = (progress + 16).toString()
                binding.tvTestSize.textSize = (progress + 16).toFloat()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                binding.tvTestSize.visibility = View.VISIBLE
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                SaveSharedP.data(requireContext(), "size_text", textSize.toString())
                Handler().postDelayed({
                    binding.tvTestSize.visibility = View.GONE
                }, 2500)

            }
        })



//        binding.tvAboutUs.setOnClickListener{
//            findNavController().navigate(R.id.action_settingFragment_to_aboutUsFragment)
//        }
//
//        binding.tvContactUs.setOnClickListener {
//            findNavController().navigate(R.id.action_settingFragment_to_contactUsFragment)
//        }
    }

}