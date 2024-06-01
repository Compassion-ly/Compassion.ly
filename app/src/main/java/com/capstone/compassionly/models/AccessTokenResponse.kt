package com.capstone.compassionly.models

import com.google.gson.annotations.SerializedName

data class AccessTokenResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class Data(

	@field:SerializedName("access_token")
	val accessToken: String? = null,

	@field:SerializedName("token_type")
	val tokenType: String? = null,

	@field:SerializedName("user")
	val user: User? = null
)

data class AccessTokenRequest(
	val token: String
)

