package com.capstone.compassionly.datasource.network

import com.capstone.compassionly.models.forsending.AccessToken
import com.capstone.compassionly.models.LoginResponse
import com.capstone.compassionly.models.User
import com.capstone.compassionly.models.forsending.BodyUpdateProfile
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface HitPointService {

    @POST("/api/v1/auth/access-token")
    suspend fun accesToken(
        @Body accessToken: AccessToken
    ): LoginResponse

    @POST("/api/v1/users/save-user")
    suspend fun updatePersonalData(
       @Body userUpdateSend: UserUpdateSend
    ): Response<SuccessResponse<UserModel>>

    @GET("/api/v1/users/me")
    suspend fun getMe(): Response<SuccessResponse<DetailUserModel>>

    @POST("/api/v1/auth/logout")
    suspend fun logout(
        @Query("token") token:String
    ): Response<SuccessResponse<String>>

    @GET("/api/v1/schools/list-schools")
    suspend fun getSchoolList() : Response<SuccessResponse<List<SchoolModel>>>

    @GET("/api/v1/schools/list-school-majors")
    suspend fun getSchoolMajorList() : Response<SuccessResponse<List<SchoolMajor>>>
}