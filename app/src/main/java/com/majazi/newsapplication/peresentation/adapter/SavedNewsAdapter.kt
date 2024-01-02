package com.majazi.newsapplication.peresentation.adapter

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.majazi.newsapplication.R
import com.majazi.newsapplication.data.model.DataSavedList
import com.majazi.newsapplication.data.utils.SaveSharedP
import com.majazi.newsapplication.databinding.ItemListNewsBinding
import java.lang.NullPointerException

class SavedNewsAdapter : RecyclerView.Adapter<SavedNewsAdapter.MyViewHolder>() {


    private val callback = object : DiffUtil.ItemCallback<DataSavedList>(){
        override fun areItemsTheSame(oldItem: DataSavedList, newItem: DataSavedList): Boolean {
            return oldItem.id == newItem.id
        }


        override fun areContentsTheSame(oldItem: DataSavedList, newItem: DataSavedList): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,callback)

    inner class MyViewHolder(val binding:ItemListNewsBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(data: DataSavedList){
            try {
                Glide.with(binding.imageListNews.context)
                    .load(data.image.mediumImage)
                    .into(binding.imageListNews)
            }catch (e: NullPointerException){
                e.printStackTrace()
            }


            checkTextSize(itemView.context,binding.tvHeder,binding.tvDate)
            binding.tvHeder.text = data.title
            binding.tvDate.text = data.created

            binding.imbSaveNews.setImageResource(R.drawable.baseline_bookmark_24)

            binding.imbSaveNews.setOnClickListener {
                onDeleteButtonClick?.let {
                    it(data)
                }
            }

            binding.root.setOnClickListener { itt->
                Log.i("TAG", "bind: ${data.id}")

                val bundle = Bundle().apply {
                    putString("id",data.id.toString())
                }
                Navigation.findNavController(itt)
                    .navigate(R.id.action_savedNewsFragment_to_detailNewsFragment,
                    bundle)
            }

        }
    }

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

    private fun checkTextSize(context: Context, tvHeader: TextView, tvDate: TextView) {
        var textSize:String?= SaveSharedP.fetch(context,"size_text")
        if (textSize.equals("")){
            textSize = "14"
        }
        tvHeader.textSize = textSize?.toFloat()!!
        tvDate.textSize = (textSize.toFloat())-4
    }

    private var onDeleteButtonClick :((DataSavedList)->Unit)?=null

    fun setOnDeleteButtonClick(listener:(DataSavedList)->Unit){
        onDeleteButtonClick = listener
    }


}