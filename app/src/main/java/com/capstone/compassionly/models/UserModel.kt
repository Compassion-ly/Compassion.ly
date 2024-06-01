package com.capstone.compassionly.models

import com.google.gson.annotations.SerializedName

data class UserModel(

	@field:SerializedName("uid")
	val uid: String? = null,

	@field:SerializedName("is_active")
	val isActive: Boolean? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("school_id")
	val schoolId: Int? = null,

	@field:SerializedName("last_name")
	val lastName: String? = null,

	@field:SerializedName("phone_number")
	val phoneNumber: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("first_name")
	val firstName: String? = null,

	@field:SerializedName("school_major_id")
	val schoolMajorId: Int? = null,

	@field:SerializedName("email")
	val email: String? = null
)

data class AccessToken(

	@field:SerializedName("access_token")
	val accessToken: String? = null,

	@field:SerializedName("token_type")
	val tokenType: String? = null,

	@field:SerializedName("user")
	val user: UserModel? = null
)

data class DetailUserModel (

	@field:SerializedName("school")
	val school: SchoolModel? = null,

	@field:SerializedName("school_major")
	val schoolMajor: SchoolMajor? = null,

	@field:SerializedName("user")
	val user: UserModel

)
