package com.capstone.compassionly.models

import com.google.gson.annotations.SerializedName

data class CollegesByMajorResponse(

	@field:SerializedName("data")
	val data: List<DataItemCollegesByMajor?>? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class DataItemCollegesByMajor(

	@field:SerializedName("college_city")
	val collegeCity: String? = null,

	@field:SerializedName("college_name")
	val collegeName: String? = null,

	@field:SerializedName("college_province")
	val collegeProvince: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)
