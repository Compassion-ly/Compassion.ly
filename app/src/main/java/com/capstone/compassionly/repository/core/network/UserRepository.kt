package com.capstone.compassionly.repository.core.network


import android.util.Log
import com.capstone.compassionly.datasource.network.ApiConfiguration
import com.capstone.compassionly.datasource.network.HitPointService
import com.capstone.compassionly.models.AccessTokenRequest
import com.capstone.compassionly.models.AccessTokenResponse
import com.capstone.compassionly.models.LoginResponse
import retrofit2.Response


class UserRepository(private val hitPointService: HitPointService) {
    suspend fun sendToken(token: String): Response<AccessTokenResponse> {
        val body = AccessTokenRequest(token)
        Log.d("UserRepository", "Sending token: $token")
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