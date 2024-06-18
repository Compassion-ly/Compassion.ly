package com.capstone.compassionly.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.compassionly.R
import com.capstone.compassionly.databinding.ItemDetailTopicBinding
import com.capstone.compassionly.databinding.ItemMajorForGridBinding
import com.capstone.compassionly.models.MajorsItem
import com.capstone.compassionly.models.TopicModel

class ItemMajorGridAdapter() : RecyclerView.Adapter<ItemMajorGridAdapter.DetailViewHolder>() {

    private var listMajor : List<MajorsItem>? = null

    inner class DetailViewHolder (private val binding: ItemMajorForGridBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setData(data: MajorsItem) {
            binding.apply {
                namaMajor.text = data.majorName
                interest.text = data.majorInterest.toString()
                level.text = data.majorLevel
                if (data.majorImage.isNullOrEmpty()) {
                    Glide.with(binding.root.context)
                        .load(binding.root.context.resources.getDrawable(R.drawable.image_compassion_logos, null))
                        .into(image)
                } else {
                    Glide.with(binding.root.context)
                        .load(data.majorImage)
                        .into(image)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        return DetailViewHolder(
            ItemMajorForGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = listMajor?.size!!

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        listMajor?.let { holder.setData(it[position]) }
    }

    fun save (data : List<MajorsItem>) {
        listMajor = data
    }
}