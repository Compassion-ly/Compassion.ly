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

class ListMajorAdapter(private val token: String) :
    ListAdapter<DataItem, ListMajorAdapter.MyViewHolder>(DIFF_CALLBACK) {
    class MyViewHolder(val binding: ItemMajorBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(token: String, majors: DataItem) {
            binding.tvMajorname.text = majors.majorName

            if (majors.majorImage == null) {
                Glide.with(binding.root.context)
                    .load("https://www.quipper.com/id/blog/wp-content/uploads/2020/10/175-min-min.png")
                    .into(binding.ivMajor)
            } else {
                Glide.with(binding.root.context)
                    .load(majors.majorImage)
                    .into(binding.ivMajor)
            }

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailJurusanActivity::class.java)
                intent.putExtra(DetailJurusanActivity.MAJOR_ID, majors.id)
                intent.putExtra("token", token)
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
            holder.bind(token, jurusan)
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