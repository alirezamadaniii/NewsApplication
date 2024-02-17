package com.majazi.newsapplication.peresentation.ui.setting

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatDelegate
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
        showVersionApk()
        setSizeText()
        onclick()
        nightModeButtonState()
    }

    private fun nightModeButtonState() {
        val nightMode: String? = SaveSharedP.fetch(requireContext(),"night_mode")
        val isNight:Boolean = nightMode.toBoolean()
        binding.switchDarkMode.isChecked = isNight

    }

    private fun onclick() {
        binding.lilAboutUs.setOnClickListener {
            findNavController().navigate(R.id.action_settingFragment_to_aboutUsFragment)
        }

        binding.lilContactUs.setOnClickListener {
            findNavController().navigate(R.id.action_settingFragment_to_contactUsFragment)
        }

        binding.switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                SaveSharedP.data(requireContext(),"night_mode","true")
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                SaveSharedP.data(requireContext(),"night_mode","false")
            }
        }
    }

    private fun setSizeText() {
        var textSize: String? = SaveSharedP.fetch(requireContext(), "size_text")

        if (textSize.equals("")) {
            textSize = "0"
        }else{
            binding.seekbarTextSize.progress = (textSize?.toInt()?.minus(16))!!
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
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.tvTestSize.visibility = View.GONE
                },2500)
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

    private fun showVersionApk() {
        try {
            val pInfo: PackageInfo =
                requireActivity().packageManager.getPackageInfo(requireActivity().packageName, 0)
            val version:String? = pInfo.versionName
            binding.tvVersion.text ="ورژن "+version.toString()
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
    }

}