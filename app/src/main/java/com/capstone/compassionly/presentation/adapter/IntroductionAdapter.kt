package com.capstone.compassionly.presentation.adapter

import android.util.ArrayMap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.capstone.compassionly.databinding.ItemStepOnIntroductionFeaturesBinding

class IntroductionAdapter(
    private val list: List<ArrayMap<String, Any>>
) : RecyclerView.Adapter<IntroductionAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemStepOnIntroductionFeaturesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setup(data: ArrayMap<String, Any>) {
            binding.description.text = data["description"].toString()
            binding.imageInstroduction.setImageResource((data["pictures"] as Int))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemStepOnIntroductionFeaturesBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setup(list[position])
    }
}