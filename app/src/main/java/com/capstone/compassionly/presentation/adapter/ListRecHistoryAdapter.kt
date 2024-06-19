package com.capstone.compassionly.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.capstone.compassionly.databinding.ItemRecHistoriesBinding
import com.capstone.compassionly.models.PredictionItem

class ListRecHistoryAdapter: RecyclerView.Adapter<ListRecHistoryAdapter.ViewHolder>() {

    val asyncDiffer = AsyncListDiffer(this, diffUTil)

    inner class ViewHolder(private val binding: ItemRecHistoriesBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: PredictionItem) {
            binding.apply {
                topicName.text = data.majorName
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRecHistoriesBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = asyncDiffer.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = asyncDiffer.currentList[position]
        holder.bind(item)
    }

    fun save(data: List<PredictionItem>) {
        asyncDiffer.submitList(data)
    }

    companion object {
        val diffUTil = object : DiffUtil.ItemCallback<PredictionItem> () {
            override fun areItemsTheSame(
                oldItem: PredictionItem,
                newItem: PredictionItem
            ): Boolean {
                return newItem == oldItem
            }

            override fun areContentsTheSame(
                oldItem: PredictionItem,
                newItem: PredictionItem
            ): Boolean {
                return newItem.id == oldItem.id
            }

        }
    }
}