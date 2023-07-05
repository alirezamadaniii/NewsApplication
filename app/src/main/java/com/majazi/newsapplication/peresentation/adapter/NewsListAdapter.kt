package com.majazi.newsapplication.peresentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.majazi.newsapplication.R
import com.majazi.newsapplication.data.model.newslist.Data
import com.majazi.newsapplication.databinding.ItemListNewsBinding
import java.lang.NullPointerException

class NewsListAdapter: RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>() {


    private var stateSavedButton = false

    private val callback = object : DiffUtil.ItemCallback<Data>(){
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id == newItem.id
        }


        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,callback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemListNewsBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bind(item)
    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }



    inner class  NewsViewHolder(val binding: ItemListNewsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(itemNews: Data){
            try {
                Glide.with(binding.imageListNews.context)
                    .load(itemNews.image.mediumImage)
                    .into(binding.imageListNews)
            }catch (e:NullPointerException){
                e.printStackTrace()
            }


            binding.tvHeder.text = itemNews.title
            binding.tvDate.text = itemNews.created

            binding.root.setOnClickListener {
                onItemClick?.let {
                    it(itemNews)
                }
            }

            binding.imbSaveNews.setOnClickListener {
                stateSavedButton = if (!stateSavedButton){
                    binding.imbSaveNews.setImageResource(R.drawable.baseline_bookmark_24)
                    true
                }else{
                    binding.imbSaveNews.setImageResource(R.drawable.baseline_bookmark_border_24)
                    false
                }

                onSavedButtonClick?.let {
                    val data = itemNews
                    data.isSave =true
                    it(itemNews)
                }
            }
        }
    }

    private var onItemClick :((Data)->Unit)?=null
    private var onSavedButtonClick :((Data)->Unit)?=null



    fun setOnItemClick(listener:(Data)->Unit){
        onItemClick = listener
    }
    fun setOnSavedButtonClick(listener:(Data)->Unit){
        onSavedButtonClick = listener
    }

}