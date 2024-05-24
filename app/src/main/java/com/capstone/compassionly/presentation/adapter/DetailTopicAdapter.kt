package com.capstone.compassionly.presentation.adapter

import android.adservices.topics.Topic
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.capstone.compassionly.databinding.ItemDetailTopicBinding
import com.capstone.compassionly.databinding.ItemRandomTopicBinding
import com.capstone.compassionly.models.TopicModel
import com.capstone.compassionly.presentation.feature.topic.DetailTopicActivity

class DetailTopicAdapter : RecyclerView.Adapter<DetailTopicAdapter.DetailViewHolder>() {

    private var topic : TopicModel? = null

    inner class DetailViewHolder (private val binding: ItemDetailTopicBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setData(data: TopicModel, position: Int) {
            val dataMaterial = data.material[position]
            val adapterImage = ImageMaterialAdapter()
            adapterImage.save(dataMaterial.listImage)
            binding.apply {
                headTopic.text = data.topic
                mainTopic.text = dataMaterial.course
                pagerImageDetail.adapter = adapterImage
                dotsIndicator.attachTo(pagerImageDetail)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        return DetailViewHolder(
            ItemDetailTopicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = topic?.material?.size!!

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        topic?.let { holder.setData(it, position) }
    }

    fun save (data : TopicModel) {
        topic = data
    }
}