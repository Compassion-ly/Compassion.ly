package com.capstone.compassionly.datasource.network

import com.capstone.compassionly.models.CollegesByMajorResponse
import com.capstone.compassionly.models.DetailUserModel
import com.capstone.compassionly.models.LoginResponse
import com.capstone.compassionly.models.MajorResponse
import com.capstone.compassionly.models.SchoolMajor
import com.capstone.compassionly.models.SchoolModel
import com.capstone.compassionly.models.SuccessResponse
import com.capstone.compassionly.models.User
import com.capstone.compassionly.models.forsending.AccessToken
import com.capstone.compassionly.models.forsending.UserUpdateSend
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface HitPointService {

    @POST("/api/v1/auth/access-token")
    suspend fun accessToken(
        @Body accessToken: AccessToken
    ): LoginResponse

    @POST("/api/v1/users/save-user")
    suspend fun updatePersonalData(
        @HeaderMap headerMap: Map<String, String>,
        @Body userUpdateSend: UserUpdateSend
    ): Response<SuccessResponse<User>>

    @GET("/api/v1/users/me")
    suspend fun getMe(
        @HeaderMap headerMap: Map<String, String>,
    ): Response<SuccessResponse<DetailUserModel>>

    //major
    @GET("/api/v1/colleges/list-college-majors")
    suspend fun getMajors(
        @HeaderMap headerMap: Map<String, String>,
        ): MajorResponse

    @GET("/api/v1/colleges/list-college-majors")
    suspend fun getMajor(
        @HeaderMap headerMap: Map<String, String>,
        @Query("search_query") searchQuery: String
    ): MajorResponse

    @GET("/api/v1/colleges/list-colleges-by-major/{major_id}")
    suspend fun getCollegesByMajor(
        @HeaderMap headerMap: Map<String, String>,
        @Path("major_id") majorId: Int
    ): CollegesByMajorResponse

    @POST("/api/v1/auth/logout")
    suspend fun logout(
        @Query("token") token: String
    ): Response<SuccessResponse<String>>

    @GET("/api/v1/schools/list-schools")
    suspend fun getSchoolList(): Response<SuccessResponse<List<SchoolModel>>>

    @GET("/api/v1/schools/list-school-majors")
    suspend fun getSchoolMajorList(): Response<SuccessResponse<List<SchoolMajor>>>
}