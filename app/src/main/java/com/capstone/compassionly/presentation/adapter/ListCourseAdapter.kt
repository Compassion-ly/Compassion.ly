package com.capstone.compassionly.presentation.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capstone.compassionly.databinding.ItemCourseBinding
import com.capstone.compassionly.presentation.feature.pengantar_jurusan.datadummy.Course
import com.capstone.compassionly.presentation.feature.pengantar_jurusan.DetailJurusanActivity
import com.capstone.compassionly.presentation.feature.pengantar_jurusan.DetailMatkulActivity

class ListCourseAdapter :
    ListAdapter<Course, ListCourseAdapter.MyViewHolder>(ListCourseAdapter.DIFF_CALLBACK) {
    class MyViewHolder(val binding: ItemCourseBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(course: Course) {
            binding.tvCoursename.text = course.courseName
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailMatkulActivity::class.java)
                intent.putExtra(DetailMatkulActivity.COURSE_ID, course.courseId)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemCourseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder:MyViewHolder, position: Int) {
        val course = getItem(position)
        holder.bind(course)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Course>() {
            override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean {
                return oldItem == newItem
            }
        }
    }
}