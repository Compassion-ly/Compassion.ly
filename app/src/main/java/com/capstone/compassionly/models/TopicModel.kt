package com.capstone.compassionly.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TopicModel(

	@field:SerializedName("topic_category_id")
	val topicCategoryId: Int? = null,

	@field:SerializedName("topic_explanation")
	val topicExplanation: String? = null,

	@field:SerializedName("topic_name")
	val topicName: String? = null,

	@field:SerializedName("topic_weight")
	val topicWeight: List<Float>? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("topic_image2")
	val topicImage2: String? = null,

	@field:SerializedName("short_introduction")
	val shortIntroduction: String? = null,

	@field:SerializedName("topic_image")
	val topicImage: String? = null
): Parcelable
