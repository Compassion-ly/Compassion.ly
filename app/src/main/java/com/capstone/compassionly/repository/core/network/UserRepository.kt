package com.capstone.compassionly.repository.core.network


import android.util.Log
import androidx.lifecycle.liveData
import com.capstone.compassionly.datasource.network.HitPointService
import com.capstone.compassionly.models.AccessToken
import com.capstone.compassionly.models.ErrorModel
import com.capstone.compassionly.utility.Resources
import com.google.gson.Gson
import retrofit2.HttpException


class UserRepository(private val hitPointService: HitPointService) {

    fun sendToken(token: String) = liveData {
        emit(Resources.Loading)
        try {
            val accessToken = AccessToken(token)
            val response = hitPointService.accesToken(accessToken)
            Log.d("UserRepository", "$response")
            emit(Resources.Success(response))
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorModel::class.java)
            val errorMessage = errorBody.detail
            emit(Resources.Error(errorMessage!!))
        }
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(
            hitPointService: HitPointService
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(hitPointService)
            }.also { instance = it }
    }
}