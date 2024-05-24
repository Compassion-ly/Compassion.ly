package com.capstone.compassionly.presentation.adapter

import android.adservices.topics.Topic
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.capstone.compassionly.databinding.ItemDetailTopicBinding
import com.capstone.compassionly.databinding.ItemImageDetailBinding
import com.capstone.compassionly.databinding.ItemRandomTopicBinding
import com.capstone.compassionly.models.ImageMaterial
import com.capstone.compassionly.models.TopicModel
import com.capstone.compassionly.presentation.feature.topic.DetailTopicActivity

class ImageMaterialAdapter : RecyclerView.Adapter<ImageMaterialAdapter.ImageViewHolder>() {

    private var imageList : List<ImageMaterial>? = null

    inner class ImageViewHolder (private val binding: ItemImageDetailBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setData(data: ImageMaterial) {
            binding.apply {
                binding.detailTopicPicture.setImageResource(data.detailImage)
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

    fun save (data : List<ImageMaterial>) {
        imageList = data
    }
}