package com.capstone.compassionly.models

import com.google.gson.annotations.SerializedName

data class DetailCourseResponse(

	@field:SerializedName("data")
	val data: DataCourse? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class DataCourse(

	@field:SerializedName("course")
	val course: Course? = null
)

data class Course(

	@field:SerializedName("course_explain")
	val courseExplain: Any? = null,

	@field:SerializedName("course_definition")
	val courseDefinition: String? = null,

	@field:SerializedName("course_name")
	val courseName: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("course_image")
	val courseImage: Any? = null
)
