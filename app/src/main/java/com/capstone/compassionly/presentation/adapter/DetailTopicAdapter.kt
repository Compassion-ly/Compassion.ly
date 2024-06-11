package com.capstone.compassionly.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.capstone.compassionly.databinding.ItemDetailTopicBinding
import com.capstone.compassionly.models.TopicModel

class DetailTopicAdapter(private val context: Context) : RecyclerView.Adapter<DetailTopicAdapter.DetailViewHolder>() {

    private var topic : TopicModel? = null

    inner class DetailViewHolder (private val binding: ItemDetailTopicBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setData(data: TopicModel, position: Int) {
//            val dataImage = mutableListOf<String>()
//            dataImage.add(data.topicImage!!)
//            dataImage.add(data.topicImage2!!)
//            val adapterImage = ImageMaterialAdapter(context)
//            adapterImage.save(dataImage)
//            binding.apply {
//                headTopic.text = data.topicName
//                mainTopic.text = data.topicExplanation
//                pagerImageDetail.adapter = adapterImage
//                dotsIndicator.attachTo(pagerImageDetail)
//            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        return DetailViewHolder(
            ItemDetailTopicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        topic?.let { holder.setData(it, position) }
    }

    fun save (data : TopicModel) {
        topic = data
    }
}