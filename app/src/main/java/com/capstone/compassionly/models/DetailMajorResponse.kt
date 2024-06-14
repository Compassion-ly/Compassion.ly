package com.capstone.compassionly.models

import com.google.gson.annotations.SerializedName

data class DetailMajorResponse(

    @field:SerializedName("data")
    val data: Data? = null,

    @field:SerializedName("message")
    val message: String? = null
)

data class Data(

    @field:SerializedName("for_who")
    val forWho: String? = null,

    @field:SerializedName("major_interest")
    val majorInterest: Int? = null,

    @field:SerializedName("major_name")
    val majorName: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("major_image")
    val majorImage: String? = null,

    @field:SerializedName("major_definition")
    val majorDefinition: String? = null,

    @field:SerializedName("major_level")
    val majorLevel: String? = null
)