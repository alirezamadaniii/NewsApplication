package com.majazi.newsapplication.peresentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.majazi.newsapplication.R
import com.majazi.newsapplication.data.model.detailnews.comment.Data
import com.majazi.newsapplication.databinding.ItemCommentBinding
import java.lang.NullPointerException

class CommentAdapter: RecyclerView.Adapter<CommentAdapter.MyViewHolder>() {

//    private val random = java.util.Random()

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
        val binding = ItemCommentBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bind(item)
    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }



    inner class  MyViewHolder(val binding: ItemCommentBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Data){
            try {
                val image= arrayListOf<Int>(
                    R.drawable.avater1,
                    R.drawable.avater2,
                    R.drawable.avater3,
                    R.drawable.avater4,
                    R.drawable.avater5,
                    R.drawable.avater6,
                    R.drawable.avater7,
                    R.drawable.avater8,
                    R.drawable.avater9,
                    R.drawable.avater10
                )
                Glide.with(binding.imgComment.context)
                    .load(image[2])
                    .into(binding.imgComment)
            }catch (e: NullPointerException){
                e.printStackTrace()
            }


            binding.tvUsernameComment.text = data.user.first_name+data.user.last_name
            binding.tvComment.text = data.comment
            binding.tvDateComment.text = data.date

            }

//        fun random():Int{
//            return random.nextInt(0-10)+10
//        }
        }


}