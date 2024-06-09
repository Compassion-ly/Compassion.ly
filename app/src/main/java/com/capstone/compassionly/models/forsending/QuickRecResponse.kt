package com.capstone.compassionly.models.forsending

import com.google.gson.annotations.SerializedName

data class QuickRecResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class Data(

	@field:SerializedName("additionalProp1")
	val additionalProp1: List<String?>? = null,

	@field:SerializedName("additionalProp3")
	val additionalProp3: List<String?>? = null,

	@field:SerializedName("additionalProp2")
	val additionalProp2: List<String?>? = null
)
