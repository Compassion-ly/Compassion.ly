package com.capstone.compassionly.models

import androidx.room.Entity

@Entity
data class LocalHistoryTopic(
    val id: Int,
    val rating: Int,
    val topicId: Int,
    val topicName: String,
)
