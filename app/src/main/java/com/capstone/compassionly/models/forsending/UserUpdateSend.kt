package com.capstone.compassionly.models.forsending

import com.google.gson.annotations.SerializedName

data class UserUpdateSend(

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("school_id")
	val schoolId: Int? = null,

	@field:SerializedName("last_name")
	val lastName: String? = null,

	@field:SerializedName("phone_number")
	val phoneNumber: String? = null,

	@field:SerializedName("first_name")
	val firstName: String? = null,

	@field:SerializedName("school_major_id")
	val schoolMajorId: Int? = null
)
