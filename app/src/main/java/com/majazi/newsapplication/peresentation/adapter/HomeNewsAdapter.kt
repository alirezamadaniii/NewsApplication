package com.majazi.newsapplication.peresentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.majazi.newsapplication.data.model.homenews.ItemNews
import com.majazi.newsapplication.databinding.ItemHomeBinding

class HomeNewsAdapter: RecyclerView.Adapter<HomeNewsAdapter.NewsViewHolder>() {


    private val callback = object :DiffUtil.ItemCallback<ItemNews>(){
        override fun areItemsTheSame(oldItem: ItemNews, newItem: ItemNews): Boolean {
            return oldItem.id == newItem.id
        }


        override fun areContentsTheSame(oldItem: ItemNews, newItem: ItemNews): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,callback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemHomeBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        return NewsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bind(item)
    }


    inner class  NewsViewHolder(val binding: ItemHomeBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(itemNews:ItemNews){
            binding.txtCategoryHome.text = itemNews.categoryName
          Glide.with(binding.imageViewExplore.context)
              .load(itemNews.image)
              .into(binding.imageViewExplore)

            binding.root.setOnClickListener {
                onItemClick?.let {
                    it(itemNews)
                }
            }
        }
    }

    private var onItemClick :((ItemNews)->Unit)?=null

    fun setOnItemClick(listener:(ItemNews)->Unit){
        onItemClick = listener
    }

}