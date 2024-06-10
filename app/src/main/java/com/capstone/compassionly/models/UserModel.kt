package com.capstone.compassionly.models

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

data class UserModel(
	@field:SerializedName("access_token")
	val accessToken: String,

	@field:SerializedName("token_type")
	val tokenType: String,

	@field:SerializedName("user")
	val user: User
)

@Entity
data class User(

	@field:SerializedName("uid")
	val uid: String? = null,

	@field:SerializedName("is_active")
	val isActive: Boolean? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("user_schools_id")
	val userSchoolsId: Int,

	@field:SerializedName("user_topic_weight")
	val userTopicWeight: List<Int>,

	@field:SerializedName("last_name")
	val lastName: String? = null,

	@field:SerializedName("phone_number")
	val phoneNumber: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("first_name")
	val firstName: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)

data class DetailUserModel (

	@field:SerializedName("school")
	val school: SchoolModel?,

	@field:SerializedName("school_major")
	val schoolMajor: SchoolMajor?,

	@field:SerializedName("user")
	val user: User?

)
