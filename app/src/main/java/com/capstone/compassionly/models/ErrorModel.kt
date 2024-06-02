package com.capstone.compassionly.models

import com.google.gson.annotations.SerializedName

data class ErrorModel(

	@field:SerializedName("detail")
	val detail: List<DetailItem?>? = null
)

data class DetailItem(

	@field:SerializedName("msg")
	val msg: String? = null,

	@field:SerializedName("loc")
	val loc: List<String?>? = null,

	@field:SerializedName("type")
	val type: String? = null
)
