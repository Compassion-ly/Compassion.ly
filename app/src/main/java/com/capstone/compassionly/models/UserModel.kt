package com.capstone.compassionly.models

import com.google.gson.annotations.SerializedName

data class UserModel(
	@field:SerializedName("access_token")
	val accessToken: String,

	@field:SerializedName("token_type")
	val tokenType: String,

	@field:SerializedName("user")
	val user: User
)

data class User(

	@field:SerializedName("uid")
	val uid: String,

	@field:SerializedName("is_active")
	val isActive: Boolean,

	@field:SerializedName("gender")
	val gender: String,

	@field:SerializedName("user_schools_id")
	val userSchoolsId: Int,

	@field:SerializedName("last_name")
	val lastName: String,

	@field:SerializedName("phone_number")
	val phoneNumber: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("first_name")
	val firstName: String,

	@field:SerializedName("email")
	val email: String
)




