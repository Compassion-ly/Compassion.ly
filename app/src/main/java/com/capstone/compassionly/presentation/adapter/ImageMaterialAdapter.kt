package com.capstone.compassionly.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.compassionly.databinding.ItemImageDetailBinding

class ImageMaterialAdapter() : RecyclerView.Adapter<ImageMaterialAdapter.ImageViewHolder>() {

    private var imageList : List<String>? = null

    inner class ImageViewHolder (private val binding: ItemImageDetailBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setData(data: String) {
            binding.apply {
                Glide.with(binding.root.context)
                    .load(data)
                    .into(binding.detailTopicPicture)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            ItemImageDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = imageList?.size!!

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        imageList?.let { holder.setData(it[position]) }
    }

    fun save (data : List<String>) {
        imageList = data
    }
}