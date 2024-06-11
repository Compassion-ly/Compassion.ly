package com.capstone.compassionly.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capstone.compassionly.databinding.ItemCategoryBinding
import com.capstone.compassionly.models.local.Interest

class ListCategoryAdapter :
    ListAdapter<Interest, ListCategoryAdapter.MyViewHolder>(DIFF_CALLBACK) {
    class MyViewHolder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(interest: Interest) {
            binding.tvInterestname.text = interest.getResult.joinToString(", ")        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder:MyViewHolder, position: Int) {
        val category = getItem(position)
        holder.bind(category)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Interest>() {
            override fun areItemsTheSame(oldItem: Interest, newItem: Interest): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Interest, newItem: Interest): Boolean {
                return oldItem == newItem
            }
        }
    }
}