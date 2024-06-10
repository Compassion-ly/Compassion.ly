package com.capstone.compassionly.datasource.local

import androidx.room.TypeConverter
import com.capstone.compassionly.models.DetailUserModel
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

}