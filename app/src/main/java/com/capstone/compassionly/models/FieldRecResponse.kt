package com.capstone.compassionly.models

import com.google.gson.annotations.SerializedName

data class FieldRecResponse(

	@field:SerializedName("data")
	val data: DataFieldRec? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class DataFieldRec(

	@field:SerializedName("top_topics")
	val topTopics: List<String?>? = null
)
