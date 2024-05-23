package com.capstone.compassionly.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.compassionly.databinding.ItemMajorRecBinding
import com.capstone.compassionly.presentation.feature.show_recommendation.datadummy.Major

class ListMajorRecAdapter: ListAdapter<Major, ListMajorRecAdapter.MyViewHolder>(DIFF_CALLBACK) {
    class MyViewHolder(val binding: ItemMajorRecBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(major: Major){
            binding.tvMajorname.text = major.majorName
            Glide.with(binding.root.context)
                .load(major.majorImage)
                .into(binding.ivMajor)
//            itemView.setOnClickListener {
//                val intent = Intent(itemView.context, DetailJurusanActivity::class.java)
//                intent.putExtra(DetailJurusanActivity.MAJOR_ID, major.majorId)
//                itemView.context.startActivity(intent)
//            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemMajorRecBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val jurusan = getItem(position)
        holder.bind(jurusan)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Major>() {
            override fun areItemsTheSame(oldItem: Major, newItem: Major): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: Major, newItem: Major): Boolean {
                return oldItem == newItem
            }
        }
    }
}