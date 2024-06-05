package com.capstone.compassionly.models.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocalUser (
    @PrimaryKey
    val id : Int?,
    val gender: String? = null,
    val userSchoolsId: Int,
    val lastName: String? = null,
    val phoneNumber: String? = null,
    val firstName: String? = null,
    val email: String? = null,
    val schoolName: String? = null,
    val schoolCity: String? = null,
    val npsn: String? = null,
    val schoolProvince: String? = null,
    val schoolMajorName: String? = null
)