package com.majazi.newsapplication.peresentation.ui.listnews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.majazi.newsapplication.R
import com.majazi.newsapplication.databinding.LayoutLoadStateBinding

class DefaultLoadStateAdapter (
    private val retry: () -> Unit
) : LoadStateAdapter<DefaultLoadStateAdapter.ViewHolder>() {

    class ViewHolder(val binding: LayoutLoadStateBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        loadState: LoadState
    ) {
        holder.binding.refreshStateBtn.isVisible = loadState !is LoadState.Loading
        holder.binding.loadProgress.isVisible = loadState is LoadState.Loading

        holder.binding.refreshStateBtn.setOnClickListener {
            retry()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.layout_load_state,
                parent,
                false
            )
        )
    }
}