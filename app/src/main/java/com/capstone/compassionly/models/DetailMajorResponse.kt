package com.capstone.compassionly.models

import com.google.gson.annotations.SerializedName

data class DetailMajorResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class Data(

	@field:SerializedName("courses")
	val courses: List<CoursesItem?>? = null,

	@field:SerializedName("major")
	val major: Major? = null,

	@field:SerializedName("prospects")
	val prospects: List<ProspectsItem?>? = null
)

data class CoursesItem(
	@field:SerializedName("course_name")
	val courseName: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,
)

data class ProspectsItem(

	@field:SerializedName("future_prospect_name")
	val futureProspectName: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class Major(

	@field:SerializedName("for_who")
	val forWho: String? = null,

	@field:SerializedName("major_interest")
	val majorInterest: Int? = null,

	@field:SerializedName("major_name")
	val majorName: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("major_image")
	val majorImage: Any? = null,

	@field:SerializedName("major_definition")
	val majorDefinition: String? = null,

	@field:SerializedName("major_level")
	val majorLevel: String? = null
)
