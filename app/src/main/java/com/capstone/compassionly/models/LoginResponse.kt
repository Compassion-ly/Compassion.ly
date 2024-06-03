package com.capstone.compassionly.models

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("data")
	val data: DataLogin? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class DataLogin(

	@field:SerializedName("access_token")
	val accessToken: String? = null,

	@field:SerializedName("token_type")
	val tokenType: String? = null,

	@field:SerializedName("user")
	val user: UserLogin? = null
)

data class UserLogin(

	@field:SerializedName("uid")
	val uid: Any? = null,

	@field:SerializedName("is_active")
	val isActive: Boolean? = null,

	@field:SerializedName("gender")
	val gender: Any? = null,

	@field:SerializedName("school_id")
	val schoolId: Any? = null,

	@field:SerializedName("last_name")
	val lastName: Any? = null,

	@field:SerializedName("phone_number")
	val phoneNumber: Any? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("first_name")
	val firstName: Any? = null,

	@field:SerializedName("school_major_id")
	val schoolMajorId: Any? = null,

	@field:SerializedName("email")
	val email: String? = null
)
