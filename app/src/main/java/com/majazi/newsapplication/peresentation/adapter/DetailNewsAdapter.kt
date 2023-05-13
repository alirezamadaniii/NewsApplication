package com.majazi.newsapplication.peresentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.majazi.newsapplication.data.model.detailnews.AdditionalContent
import com.majazi.newsapplication.databinding.ItemHomeBinding
import com.majazi.newsapplication.databinding.ItemImageTypeBinding
import com.majazi.newsapplication.databinding.ItemTextTypeBinding

class DetailNewsAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val IMAGE_TYPE = 0
    private val VIDEO_TYPE = 1
    private val TEXT_TYPE = 2

    private val callback = object : DiffUtil.ItemCallback<AdditionalContent>(){
        override fun areItemsTheSame(oldItem: AdditionalContent, newItem: AdditionalContent): Boolean {
            return oldItem.image == newItem.image
        }


        override fun areContentsTheSame(oldItem: AdditionalContent, newItem: AdditionalContent): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,callback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when(viewType){
            TEXT_TYPE->{
                val binding = ItemTextTypeBinding
                    .inflate(LayoutInflater.from(parent.context),parent,false)
                return TextViewHolder(binding)
            }

            IMAGE_TYPE->{
                val binding = ItemImageTypeBinding
                    .inflate(LayoutInflater.from(parent.context),parent,false)
                return ImageViewHolder(binding)
            }


//            VIDEO_TYPE->{
//                val binding = ItemHomeBinding
//                    .inflate(LayoutInflater.from(parent.context),parent,false)
//                return ImageViewHolder(binding)
//            }

            else -> {
                val binding = ItemTextTypeBinding
                    .inflate(LayoutInflater.from(parent.context),parent,false)
                return TextViewHolder(binding)
            }
//            else -> {val binding = ItemImageTypeBinding
//                .inflate(LayoutInflater.from(parent.context),parent,false)
//                return ImageViewHolder(binding)
//
//            }
        }



    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = differ.currentList[position]
        when(item.type){
            "image"->(holder as ImageViewHolder).bind(item)
            "text"->(holder as TextViewHolder).bind2(item)
        }




    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }




    override fun getItemViewType(position: Int): Int {
        return when (differ.currentList[position].type){
                "image" -> IMAGE_TYPE
                "text" ->  TEXT_TYPE
                "video" -> VIDEO_TYPE
                else -> -1

        }
    }


    inner class ImageViewHolder(val binding:ItemImageTypeBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item:AdditionalContent){
            Glide.with(binding.imageType.context)
                .load(item.image.original_image)
                .into(binding.imageType)
        }
    }

    inner class TextViewHolder(val binding:ItemTextTypeBinding):RecyclerView.ViewHolder(binding.root){
        fun bind2(item:AdditionalContent){
            binding.textType.text = item.text
        }
    }

}