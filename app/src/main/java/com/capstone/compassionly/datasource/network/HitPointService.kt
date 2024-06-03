package com.capstone.compassionly.datasource.network

import com.capstone.compassionly.models.forsending.AccessToken
import com.capstone.compassionly.models.LoginResponse
import com.capstone.compassionly.models.User
import com.capstone.compassionly.models.forsending.BodyUpdateProfile
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface HitPointService {

    @POST("/api/v1/auth/access-token")
    suspend fun accesToken(
        @Body accessToken: AccessToken
    ): LoginResponse

    @FormUrlEncoded
    @POST("/api/v1/users/personal-data")
    suspend fun updatePersonalData(
        @Body bodyUpdateProfile: BodyUpdateProfile
    ): Response<User>

    @GET("/api/v1/users/me")
    suspend fun getMe(): Response<User>

}