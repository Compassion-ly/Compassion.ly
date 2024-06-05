package com.capstone.compassionly.presentation.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.compassionly.databinding.ItemMajorBinding
import com.capstone.compassionly.models.DataItem
import com.capstone.compassionly.presentation.feature.pengantar_jurusan.DetailJurusanActivity

class ListMajorAdapter : ListAdapter<DataItem, ListMajorAdapter.MyViewHolder>(DIFF_CALLBACK) {
    class MyViewHolder(val binding: ItemMajorBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(majors: DataItem) {
            binding.tvMajorname.text = majors.majorName
            Glide.with(binding.root.context)
                .load(majors.majorImage)
                .into(binding.ivMajor)
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailJurusanActivity::class.java)
                intent.putExtra(DetailJurusanActivity.MAJOR_ID, majors.id)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemMajorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val jurusan = getItem(position)
        if (jurusan != null) {
            holder.bind(jurusan)
        }
    }


    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}