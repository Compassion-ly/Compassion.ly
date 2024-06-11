package com.capstone.compassionly.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.capstone.compassionly.databinding.ItemTopicHistoriesBinding
import com.capstone.compassionly.models.local.LocalHistoryTopic

class ListHistoryAdapter: RecyclerView.Adapter<ListHistoryAdapter.ViewHolder>() {

    val asyncDiffer = AsyncListDiffer(this, diffUTil)

    inner class ViewHolder(private val binding: ItemTopicHistoriesBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: LocalHistoryTopic) {
            binding.apply {
                topicName.text = data.topicName
                topicStar.text = data.rating.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemTopicHistoriesBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = asyncDiffer.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = asyncDiffer.currentList[position]
        holder.bind(item)
    }

    fun save(data: List<LocalHistoryTopic>) {
        asyncDiffer.submitList(data)
    }

    companion object {
        val diffUTil = object : DiffUtil.ItemCallback<LocalHistoryTopic> () {
            override fun areItemsTheSame(
                oldItem: LocalHistoryTopic,
                newItem: LocalHistoryTopic
            ): Boolean {
                return newItem == oldItem
            }

            override fun areContentsTheSame(
                oldItem: LocalHistoryTopic,
                newItem: LocalHistoryTopic
            ): Boolean {
                return newItem.id == oldItem.id
            }

        }
    }
}