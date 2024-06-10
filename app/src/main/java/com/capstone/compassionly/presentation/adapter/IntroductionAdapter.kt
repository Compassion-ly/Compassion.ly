package com.capstone.compassionly.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.capstone.compassionly.databinding.ItemStepOnIntroductionFeaturesBinding
import com.capstone.compassionly.models.Step

class IntroductionAdapter(
    private val list: List<Step>
) : RecyclerView.Adapter<IntroductionAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemStepOnIntroductionFeaturesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setup(data: Step) {
            binding.description.text = data.descriptionStep
            binding.imageInstroduction.setImageResource(data.picture!!)
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