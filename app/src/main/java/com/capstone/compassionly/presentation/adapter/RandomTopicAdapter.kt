package com.capstone.compassionly.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.capstone.compassionly.databinding.ItemRandomTopicBinding
import com.capstone.compassionly.models.TopicModel

class RandomTopicAdapter : RecyclerView.Adapter<RandomTopicAdapter.TopicViewHolder>(){

    private val asyncDiffer = AsyncListDiffer(this, diffUtil)

    inner class TopicViewHolder (private val binding: ItemRandomTopicBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setData(data: TopicModel) {
            binding.apply {
                imageTopic.setImageResource(data.picture)
                topicName.text = data.topic
                shortDescription.text = data.short
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        return TopicViewHolder(
            ItemRandomTopicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = asyncDiffer.currentList.size

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        holder.setData(asyncDiffer.currentList[position])
    }

    fun save (data : List<TopicModel>) {
        asyncDiffer.submitList(data)
    }

    companion object {
        private val diffUtil = object: DiffUtil.ItemCallback<TopicModel>() {
            override fun areItemsTheSame(oldItem: TopicModel, newItem: TopicModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: TopicModel, newItem: TopicModel): Boolean {
                return newItem.id == oldItem.id
            }

        }
    }

}