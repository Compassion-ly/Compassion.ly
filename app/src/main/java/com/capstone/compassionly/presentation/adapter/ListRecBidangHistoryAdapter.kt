package com.capstone.compassionly.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.capstone.compassionly.databinding.ItemRecHistoriesBinding

class ListRecBidangHistoryAdapter: RecyclerView.Adapter<ListRecBidangHistoryAdapter.ViewHolder>() {

    val asyncDiffer = AsyncListDiffer(this, diffUTil)

    inner class ViewHolder(private val binding: ItemRecHistoriesBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: String) {
            binding.apply {
                topicName.text = data
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

    fun save(data: List<String>) {
        asyncDiffer.submitList(data)
    }

    companion object {
        val diffUTil = object : DiffUtil.ItemCallback<String> () {
            override fun areItemsTheSame(
                oldItem: String,
                newItem: String
            ): Boolean {
                return newItem == oldItem
            }

            override fun areContentsTheSame(
                oldItem: String,
                newItem: String
            ): Boolean {
                return newItem == oldItem
            }

        }
    }
}