package com.capstone.compassionly.presentation.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capstone.compassionly.databinding.ItemMajorRecBinding
import com.capstone.compassionly.models.PredictionItem
import com.capstone.compassionly.presentation.feature.pengantar_jurusan.DetailJurusanActivity

class ListMajorRecAdapter :
    ListAdapter<PredictionItem, ListMajorRecAdapter.MyViewHolder>(DIFF_CALLBACK) {
    class MyViewHolder(val binding: ItemMajorRecBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(major: PredictionItem) {
            binding.tvMajorname.text = major.majorName
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailJurusanActivity::class.java)
                intent.putExtra(DetailJurusanActivity.MAJOR_ID, major.id)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemMajorRecBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val jurusan = getItem(position)
        holder.bind(jurusan)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PredictionItem>() {
            override fun areItemsTheSame(
                oldItem: PredictionItem,
                newItem: PredictionItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: PredictionItem,
                newItem: PredictionItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}