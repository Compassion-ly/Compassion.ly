package com.capstone.compassionly.models.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.capstone.compassionly.models.DetailUserModel

@Entity
data class LocalUser (
    @PrimaryKey
    val id : Int?,
    val data: DetailUserModel?
)