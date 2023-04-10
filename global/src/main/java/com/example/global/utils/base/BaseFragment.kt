package com.example.global.utils.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<DB : ViewDataBinding>(
    @LayoutRes private val layoutResId: Int
) : Fragment() {

    private lateinit var _binding: DB

    val binding: DB
        get() = _binding

    open fun DB.initialize() {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(
            inflater,
            layoutResId,
            container,
            false
        )

        binding.initialize()

        binding.lifecycleOwner = viewLifecycleOwner

        return _binding.root
    }

}