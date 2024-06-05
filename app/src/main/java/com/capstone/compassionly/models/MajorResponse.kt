package com.capstone.compassionly.models

import com.google.gson.annotations.SerializedName

data class MajorResponse(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class DataItem(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("major_name")
	val majorName: String? = null,

	@field:SerializedName("major_image")
	val majorImage: String? = null,

	@field:SerializedName("major_definition")
	val majorDefinition: String? = null
)
