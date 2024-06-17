package com.capstone.compassionly.models

import com.google.gson.annotations.SerializedName

data class CollageByIdModel(

	@field:SerializedName("college")
	val college: CollageModel? = null,

	@field:SerializedName("majors")
	val majors: List<MajorsItem?>? = null
)

data class MajorsItem(

	@field:SerializedName("for_who")
	val forWho: String? = null,

	@field:SerializedName("major_interest")
	val majorInterest: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("major_name")
	val majorName: String? = null,

	@field:SerializedName("major_image")
	val majorImage: String? = null,

	@field:SerializedName("major_definition")
	val majorDefinition: String? = null,

	@field:SerializedName("major_level")
	val majorLevel: String? = null
)
