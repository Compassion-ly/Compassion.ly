package com.capstone.compassionly.models

import com.google.gson.annotations.SerializedName

open class SuccessResponse<T>(

	@field:SerializedName("data")
	val data: T? = null,

	@field:SerializedName("message")
	val message: String? = null
)
