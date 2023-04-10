package com.majazi.newsapplication.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.majazi.newsapplication.R
import com.majazi.newsapplication.databinding.ItemHomeBinding

class HomeAdapter() : RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {

    class MyViewHolder(private val binding: ItemHomeBinding) : RecyclerView.ViewHolder(binding.root){



        fun bind() {
            binding.imageViewExplore.setImageResource(R.drawable.ic_launcher_background)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemHomeBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_home, parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return 15
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.bind()
    }
}