package com.capstone.compassionly.models

import com.google.gson.annotations.SerializedName

data class SchoolModel(

	@field:SerializedName("school_name")
	val schoolName: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("school_city")
	val schoolCity: String? = null,

	@field:SerializedName("npsn")
	val npsn: String? = null,

	@field:SerializedName("school_province")
	val schoolProvince: String? = null
)

data class SchoolMajor (

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("school_major_name")
	val schoolMajorName: String? = null

)
