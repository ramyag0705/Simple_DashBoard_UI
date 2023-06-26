package com.example.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.databinding.ItemListBinding

class ImageAdapter(private val onClickListener: (options: ImageItem) -> Unit) :
    ListAdapter<ImageItem, ImageAdapter.CustomViewHolder>(LightShowDiffUtil) {


    inner class CustomViewHolder(
        private val context: Context,
        private val binding: ItemListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(option: ImageItem) {
            binding.profileTitle.text=option.text
            binding.profileImage.setImageResource(option.imageResId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(
            parent.context,
            ItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val option = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener(option)
        }
        holder.bind(option)
    }

    companion object {
        object LightShowDiffUtil : DiffUtil.ItemCallback<ImageItem>() {
            override fun areItemsTheSame(
                oldItem: ImageItem,
                newItem: ImageItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ImageItem,
                newItem: ImageItem
            ): Boolean {
                return oldItem.imageResId == newItem.imageResId
            }
        }
    }

}