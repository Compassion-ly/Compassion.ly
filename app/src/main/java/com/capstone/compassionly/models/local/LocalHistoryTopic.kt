package com.capstone.compassionly.models.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocalHistoryTopic(

    @PrimaryKey(true)
    val id: Int?,
    val rating: Int?,
    val topicId: Int?,
    val topicName: String?,
)
