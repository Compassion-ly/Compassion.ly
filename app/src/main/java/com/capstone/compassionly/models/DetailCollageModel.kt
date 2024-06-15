package com.capstone.compassionly.models

import com.google.gson.annotations.SerializedName

data class DetailCollageModel(

	@field:SerializedName("college_id")
	val collegeId: Int? = null,

	@field:SerializedName("interest")
	val interest: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("major_id")
	val majorId: Int? = null,

	@field:SerializedName("capacity")
	val capacity: Int? = null,

	@field:SerializedName("portofolio_type")
	val portofolioType: String? = null
)
