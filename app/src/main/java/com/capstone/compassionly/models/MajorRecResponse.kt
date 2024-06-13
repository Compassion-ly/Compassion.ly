package com.capstone.compassionly.models

import com.google.gson.annotations.SerializedName

data class MajorRecResponse(

	@field:SerializedName("data")
	val data: DataMajorRec? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class PredictionItem(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("major_name")
	val majorName: String? = null
)

data class DataMajorRec(

	@field:SerializedName("prediction")
	val prediction: List<PredictionItem?>? = null
)
