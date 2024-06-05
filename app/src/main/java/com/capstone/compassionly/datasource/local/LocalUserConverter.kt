package com.capstone.compassionly.datasource.local

import androidx.room.TypeConverter
import com.capstone.compassionly.models.DetailUserModel
import com.capstone.compassionly.models.SchoolMajor
import com.capstone.compassionly.models.SchoolModel
import com.capstone.compassionly.models.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LocalUserConverter {

    private val gson = Gson()

    @TypeConverter
    fun localUserToString(detailUserModel: DetailUserModel): String {
        return gson.toJson(detailUserModel)
    }

    @TypeConverter
    fun stringToLocalUser(json: String): DetailUserModel {
        val type = object : TypeToken<DetailUserModel>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromSchoolModel(schoolModel: SchoolModel?): String? {
        return gson.toJson(schoolModel)
    }

    @TypeConverter
    fun toSchoolModel(schoolModelString: String?): SchoolModel? {
        val type = object : TypeToken<SchoolModel>() {}.type
        return gson.fromJson(schoolModelString, type)
    }

    @TypeConverter
    fun fromSchoolMajor(schoolMajor: SchoolMajor?): String? {
        return gson.toJson(schoolMajor)
    }

    @TypeConverter
    fun toSchoolMajor(schoolMajorString: String?): SchoolMajor? {
        val type = object : TypeToken<SchoolMajor>() {}.type
        return gson.fromJson(schoolMajorString, type)
    }

    @TypeConverter
    fun fromUser(user: User?): String? {
        return gson.toJson(user)
    }

    @TypeConverter
    fun toUser(userString: String?): User? {
        val type = object : TypeToken<User>() {}.type
        return gson.fromJson(userString, type)
    }

}