package com.capstone.compassionly.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.capstone.compassionly.databinding.ItemCollageBinding
import com.capstone.compassionly.models.CollageModel

class ItemCollageMenuAdapter(
    private val callback : (Int, String) -> Unit
) : RecyclerView.Adapter<ItemCollageMenuAdapter.ViewHolder>() {

    val async = AsyncListDiffer(this, diffUtils)

    inner class ViewHolder (private val binding: ItemCollageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setup (data: CollageModel) {
            binding.name.text = data.collegeName
            binding.container.setOnClickListener {
                data.id?.let { it1 -> data.collegeName?.let { it2 -> callback(it1, it2) } }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemCollageBinding.inflate(LayoutInflater.from(parent.context), parent, false));
    }

    override fun getItemCount() = async.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = async.currentList[position];
        holder.setup(data)
    }

    fun save(data: List<CollageModel>) {
        async.submitList(data)
    }

    companion object {
        val diffUtils = object : DiffUtil.ItemCallback<CollageModel>() {
            override fun areItemsTheSame(oldItem: CollageModel, newItem: CollageModel): Boolean {
                return newItem == oldItem
            }

            override fun areContentsTheSame(oldItem: CollageModel, newItem: CollageModel): Boolean {
                return newItem.id == oldItem.id
            }
        }
    }

}