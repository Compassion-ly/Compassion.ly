package com.capstone.compassionly.presentation.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capstone.compassionly.databinding.ItemProspectBinding
import com.capstone.compassionly.models.ProspectsItem
import com.capstone.compassionly.presentation.feature.pengantar_jurusan.DetailProspectActivity

class ListProspectAdapter :
    ListAdapter<ProspectsItem, ListProspectAdapter.MyViewHolder>(DIFF_CALLBACK) {
    class MyViewHolder(val binding: ItemProspectBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(prospect: ProspectsItem) {
            binding.tvProspectname.text = prospect.futureProspectName
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailProspectActivity::class.java)
                intent.putExtra(DetailProspectActivity.PROSPECT_NAME, prospect.futureProspectName)
                intent.putExtra(DetailProspectActivity.PROSPECT_DESC, prospect.description)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemProspectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val course = getItem(position)
        holder.bind(course)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ProspectsItem>() {
            override fun areItemsTheSame(oldItem: ProspectsItem, newItem: ProspectsItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ProspectsItem,
                newItem: ProspectsItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}