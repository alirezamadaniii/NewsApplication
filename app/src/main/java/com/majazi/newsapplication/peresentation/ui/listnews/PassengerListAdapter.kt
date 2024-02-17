package com.majazi.newsapplication.peresentation.ui.listnews

import android.content.Context
import android.os.Build
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.majazi.newsapplication.R
import com.majazi.newsapplication.data.model.DataSavedList
import com.majazi.newsapplication.data.model.newslist.Data
import com.majazi.newsapplication.data.utils.SaveSharedP
import com.majazi.newsapplication.databinding.ItemListNewsBinding
import java.lang.NullPointerException

class PassengerListAdapter:
    PagingDataAdapter<Data, PassengerListAdapter.PassengerViewHolder>(diffCallback = HOME_ADAPTER_COMPARATOR) {


    private var stateSavedButton = false
    override fun onBindViewHolder(holder: PassengerViewHolder, position: Int) {
        val item: Data? = getItem(position)
        holder.bind(item!!)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PassengerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListNewsBinding.inflate(inflater)
        return PassengerViewHolder(binding)

    }

    inner class PassengerViewHolder(val binding: ItemListNewsBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind(itemNews: Data){
            try {
                Log.i("TAG", "bindwq: "+itemNews.image.mediumImage)

                Glide.with(binding.imageListNews.context)
                    .load(itemNews.image.mediumImage)
                    .into(binding.imageListNews)
            }catch (e: NullPointerException){
                e.printStackTrace()
            }

            checkTextSize(itemView.context,binding.tvHeder,binding.tvDate)
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
                    val data= DataSavedList(itemNews.created,itemNews.id,itemNews.image,itemNews.title)
                    it(data)
                }
            }
        }

    }


    private var onItemClick :((Data)->Unit)?=null
    private var onSavedButtonClick :((DataSavedList)->Unit)?=null
    private fun checkTextSize(context: Context, tvHeader: TextView, tvDate: TextView) {
        var textSize:String?= SaveSharedP.fetch(context,"size_text")
        if (textSize.equals("")){
            textSize = "14"
        }
        tvHeader.textSize = textSize?.toFloat()!!
        tvDate.textSize = (textSize.toFloat())-4
    }




    fun setOnItemClick(listener:(Data)->Unit){
        onItemClick = listener
    }
    fun setOnSavedButtonClick(listener:(DataSavedList)->Unit){
        onSavedButtonClick = listener
    }



    companion object {
        private val HOME_ADAPTER_COMPARATOR = object : DiffUtil.ItemCallback<Data>() {

            override fun areItemsTheSame(
                oldItem: Data,
                newItem: Data
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Data,
                newItem: Data
            ): Boolean =
                oldItem == newItem
        }
    }

}