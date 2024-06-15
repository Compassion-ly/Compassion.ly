package com.capstone.compassionly.models

import com.google.gson.annotations.SerializedName

data class ErrorMajorDetailModel(

    @field:SerializedName("detail")
    val detail: String? = null,

    @field:SerializedName("message")
    val message: String? = null
)