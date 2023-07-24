package com.majazi.newsapplication.peresentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.majazi.newsapplication.data.model.DataSavedList
import com.majazi.newsapplication.data.model.search.Data
import com.majazi.newsapplication.databinding.ItemListNewsBinding
import java.lang.NullPointerException

class SearchNewsAdapter:RecyclerView.Adapter<SearchNewsAdapter.MyViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<Data>(){
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id == newItem.id
        }


        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,callback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemListNewsBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bind(item)
    }

    inner class MyViewHolder(val binding:ItemListNewsBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item:Data){
            try {
                Glide.with(binding.imageListNews.context)
                    .load(item.image.medium_image)
                    .into(binding.imageListNews)
            }catch (e: NullPointerException){
                e.printStackTrace()
            }

            binding.root.setOnClickListener {
                onItemClick?.let {
                    it(item)
                }
            }

            binding.tvHeder.text = item.title
            binding.tvDate.text = item.created
        }
    }

    private var onItemClick :((Data)->Unit)?=null
    fun setOnItemClick(listener:(Data)->Unit){
        onItemClick = listener
    }
}