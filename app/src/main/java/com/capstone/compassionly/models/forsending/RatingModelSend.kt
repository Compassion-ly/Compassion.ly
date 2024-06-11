package com.capstone.compassionly.models.forsending

import com.google.gson.annotations.SerializedName

data class RatingModelSend(

    @field:SerializedName("rating")
    val rating: Int? = null,

    @field:SerializedName("topic_id")
    val topicId: Int? = null
)