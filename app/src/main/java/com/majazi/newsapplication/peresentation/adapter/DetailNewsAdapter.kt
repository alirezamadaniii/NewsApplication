package com.majazi.newsapplication.peresentation.adapter

import android.app.Application
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.majazi.newsapplication.data.model.detailnews.AdditionalContent
import com.majazi.newsapplication.data.utils.SaveSharedP
import com.majazi.newsapplication.databinding.ItemImageTypeBinding
import com.majazi.newsapplication.databinding.ItemTextTypeBinding
import com.majazi.newsapplication.databinding.ItemVideoTypeBinding


class DetailNewsAdapter(
    val context:Application,
    ): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val IMAGE_TYPE = 0
    private val VIDEO_TYPE = 1
    private val TEXT_TYPE = 2
    private lateinit var player: Player

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
                    .inflate(LayoutInflater.from(parent.context),parent ,false)
                return TextViewHolder(binding)
            }

            IMAGE_TYPE->{
                val binding = ItemImageTypeBinding
                    .inflate(LayoutInflater.from(parent.context),parent,false)
                return ImageViewHolder(binding)
            }


            VIDEO_TYPE->{
                val binding = ItemVideoTypeBinding
                    .inflate(LayoutInflater.from(parent.context),parent,false)
                return VideoViewHolder(binding)
            }


            else -> {
                val binding = ItemTextTypeBinding
                    .inflate(LayoutInflater.from(parent.context),parent,false)
                return TextViewHolder(binding)
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = differ.currentList[position]
        when(item.type){
            "image"->(holder as ImageViewHolder).bind(item)
            "text"->(holder as TextViewHolder).bind(item)
            "video"->(holder as VideoViewHolder).bind(item)
        }
    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }

     fun release(){
        player.stop()
        player.release()
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
        fun bind(item:AdditionalContent){
            binding.textType.text = item.text
            checkTextSize(itemView.context,binding.textType)
        }

        private fun checkTextSize(context: Context, tvHeader: TextView) {
            var textSize:String?= SaveSharedP.fetch(context,"size_text")
            if (textSize.equals("")){
                textSize = "16"
            }
            tvHeader.textSize = textSize?.toFloat()!!

        }
    }

    inner class VideoViewHolder(val binding:ItemVideoTypeBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item:AdditionalContent){ 
            try {
                    player = ExoPlayer.Builder(context).build()
                    binding.exoPlayer.player = player
                    // Build the media item.
                    val mediaItem: MediaItem =
                        MediaItem.fromUri(Uri.parse(item.video.original))
                    player.setMediaItem(mediaItem)
                    player.prepare()

                binding.root.setOnClickListener {
                    onItemClick?.let {
                        it(player)
                    }
                }
            }catch (e:Exception){
                e.printStackTrace()
            }

        }
    }


    private var onItemClick :((Player)->Unit)?=null

    fun setOnItemClick(listener:(Player)->Unit){
        onItemClick = listener
    }
}