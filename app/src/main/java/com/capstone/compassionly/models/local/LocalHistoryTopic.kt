package com.capstone.compassionly.models.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocalHistoryTopic(

    @PrimaryKey(true)
    val id: Int?,
    val userId: Int?,
    val rating: Int?,
    val topicId: Int?,
    @ColumnInfo("topicName")
    val topicName: String?,
)
