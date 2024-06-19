package com.capstone.compassionly.models.local

import com.google.gson.annotations.SerializedName

data class CollageModel(

	@field:SerializedName("college_name")
	val collegeName: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)
