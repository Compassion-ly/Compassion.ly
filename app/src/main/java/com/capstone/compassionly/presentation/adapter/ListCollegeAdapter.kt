package com.capstone.compassionly.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capstone.compassionly.databinding.ItemCollegeBinding
import com.capstone.compassionly.models.DataItemCollegesByMajor

class ListCollegeAdapter :
    ListAdapter<DataItemCollegesByMajor, ListCollegeAdapter.MyViewHolder>(DIFF_CALLBACK) {
    class MyViewHolder(val binding: ItemCollegeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(colleges: DataItemCollegesByMajor) {
            binding.tvCollegename.text = colleges.collegeName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemCollegeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val college = getItem(position)
        if (college != null) {
            holder.bind(college)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItemCollegesByMajor>() {
            override fun areItemsTheSame(
                oldItem: DataItemCollegesByMajor,
                newItem: DataItemCollegesByMajor
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: DataItemCollegesByMajor,
                newItem: DataItemCollegesByMajor
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}