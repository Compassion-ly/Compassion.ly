package com.capstone.compassionly.datasource.network

import com.capstone.compassionly.models.AccessToken
import com.capstone.compassionly.models.DetailUserModel
import com.capstone.compassionly.models.SchoolMajor
import com.capstone.compassionly.models.SchoolModel
import com.capstone.compassionly.models.SuccessResponse
import com.capstone.compassionly.models.UserModel
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface HitPointService {

    @FormUrlEncoded
    @POST("/api/v1/auth/access-token")
    suspend fun accessToken (
        @Field("token") token: String
    ): Response<SuccessResponse<AccessToken>>

    @FormUrlEncoded
    @POST("/api/v1/users/save-user")
    suspend fun updatePersonalData(
        @Field("first_name") firstName: String,
        @Field("last_name") lastName: String,
        @Field("phone_number") phoneNUmber: String,
        @Field("gender") gender: String,
        @Field("user_school_id") userSchoolId : Int,
        @Field("school_major_id") schoolMajorId : Int,
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