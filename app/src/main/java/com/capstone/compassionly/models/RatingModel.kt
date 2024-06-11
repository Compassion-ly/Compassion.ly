package com.capstone.compassionly.models

import com.google.gson.annotations.SerializedName

data class RatingModel(

    @field:SerializedName("user_id")
    val userId: Int? = null,

    @field:SerializedName("rating")
    val rating: Int? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("topic_id")
    val topicId: Int? = null
)