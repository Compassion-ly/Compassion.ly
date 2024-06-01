package com.capstone.compassionly.repository.core.network

import android.util.Log
import androidx.lifecycle.liveData
import com.capstone.compassionly.datasource.network.ApiConfiguration
import com.capstone.compassionly.datasource.network.HitPointService
import com.capstone.compassionly.models.AccessTokenRequest
import com.capstone.compassionly.models.AccessTokenRequest2
import com.capstone.compassionly.models.UserModel
import com.google.gson.Gson
import retrofit2.HttpException
import retrofit2.Response


class UserRepository(private val hitPointService: HitPointService) {
    suspend fun sendToken(token: String): Response<UserModel> {
        val body = AccessTokenRequest(token)
        return ApiConfiguration.hitPointService.accessToken(body)
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