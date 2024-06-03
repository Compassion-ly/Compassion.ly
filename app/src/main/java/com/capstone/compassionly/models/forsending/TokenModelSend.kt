package com.capstone.compassionly.models.forsending

import com.google.gson.annotations.SerializedName

data class TokenModelSend (
    @field:SerializedName("token")
    val token: String? = null,
)