package com.capstone.compassionly.models.forsending

import com.google.gson.annotations.SerializedName

data class MajorRecResponse(

	@field:SerializedName("data")
	val data: DataMajorRec? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class DataMajorRec(

	@field:SerializedName("prediction")
	val prediction: List<String?>? = null
)
