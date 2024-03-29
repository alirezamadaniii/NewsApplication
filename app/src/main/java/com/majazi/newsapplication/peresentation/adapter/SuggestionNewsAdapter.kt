package com.majazi.newsapplication.peresentation.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.majazi.newsapplication.data.model.interestpost.Data
import com.majazi.newsapplication.data.utils.SaveSharedP
import com.majazi.newsapplication.databinding.ItemListNewsBinding
import java.lang.NullPointerException

class SuggestionNewsAdapter: RecyclerView.Adapter<SuggestionNewsAdapter.NewsViewHolder>() {



    private val callback = object : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id == newItem.id
        }


        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemListNewsBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bind(item)
    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    inner class NewsViewHolder(val binding: ItemListNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemNews: Data) {
            try {
                Log.i("TAG", "bind: "+itemNews.toString())
                Glide.with(binding.imageListNews.context)
                    .load("https://hormoz.ir/public/"+itemNews.image.medium_image)
                    .into(binding.imageListNews)

            } catch (e: NullPointerException) {
                e.printStackTrace()
            }

            checkTextSize(itemView.context, binding.tvHeder, binding.tvDate)
            binding.tvHeder.text = itemNews.title
            binding.tvDate.text = itemNews.created

            binding.root.setOnClickListener {
                onItemClick?.let {
                    it(itemNews)
                }
            }

            binding.imbSaveNews.visibility =View.GONE

        }
    }

    private var onItemClick: ((Data) -> Unit)? = null


    private fun checkTextSize(context: Context, tvHeader: TextView, tvDate: TextView) {
        var textSize: String? = SaveSharedP.fetch(context, "size_text")
        if (textSize.equals("")) {
            textSize = "14"
        }
        tvHeader.textSize = textSize?.toFloat()!!
        tvDate.textSize = (textSize.toFloat()) - 4
    }

    fun setOnItemClick(listener: (Data) -> Unit) {
        onItemClick = listener
    }

}