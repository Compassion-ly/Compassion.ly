package com.capstone.compassionly.datasource.network

import com.capstone.compassionly.models.AccessTokenRequest
import com.capstone.compassionly.models.AccessTokenRequest2
import com.capstone.compassionly.models.User
import com.capstone.compassionly.models.UserModel
import com.capstone.compassionly.models.forsending.BodyUpdateProfile
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface HitPointService {

//    @FormUrlEncoded
//    @POST("/api/v1/auth/access-token")
//    suspend fun accessToken (
//        @Body credential: String
//    ): Response<UserModel>

//    @POST("/api/v1/auth/access-token")
//    suspend fun accessToken(
//        @Body credential: AccessTokenRequest
//    ): Response<UserModel>


    @POST("/api/v1/auth/access-token")
    suspend fun accessToken(
        @Body credential: AccessTokenRequest
    ): Response<UserModel>

    @FormUrlEncoded
    @POST("/api/v1/users/personal-data")
    suspend fun updatePersonalData(
        @Body bodyUpdateProfile: BodyUpdateProfile
    ): Response<User>

    @GET("/api/v1/users/me")
    suspend fun getMe(): Response<User>

}