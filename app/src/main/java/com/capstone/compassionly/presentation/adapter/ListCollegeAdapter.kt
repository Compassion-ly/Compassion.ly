package com.capstone.compassionly.presentation.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capstone.compassionly.databinding.ItemCollegeBinding
import com.capstone.compassionly.models.CollegesItem
import com.capstone.compassionly.presentation.feature.collage.DetailCollageActivity

class ListCollegeAdapter(private val token: String) :
    ListAdapter<CollegesItem, ListCollegeAdapter.MyViewHolder>(DIFF_CALLBACK) {
    class MyViewHolder(val binding: ItemCollegeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(token: String, colleges: CollegesItem) {
            binding.tvCollegename.text = colleges.collegeName
            itemView.setOnClickListener {
                Log.d("CollegeAdapter","${colleges.id}")
                val intent = Intent(itemView.context, DetailCollageActivity::class.java)
                intent.putExtra("id", colleges.id)
                intent.putExtra("name", colleges.collegeName)
                intent.putExtra("token", token)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemCollegeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val college = getItem(position)
        if (college != null) {
            holder.bind(token, college)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CollegesItem>() {
            override fun areItemsTheSame(
                oldItem: CollegesItem,
                newItem: CollegesItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: CollegesItem,
                newItem: CollegesItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}