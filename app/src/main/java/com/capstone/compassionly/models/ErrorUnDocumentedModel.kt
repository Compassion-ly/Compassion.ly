package com.capstone.compassionly.models

import com.google.gson.annotations.SerializedName

data class ErrorUnDocumentedModel(

	@field:SerializedName("detail")
	val detail: String? = null,

	@field:SerializedName("message")
	val message: String? = null
)
