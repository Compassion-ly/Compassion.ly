package com.capstone.compassionly.datasource.network

import com.capstone.compassionly.models.CollageModel
import com.capstone.compassionly.models.CollegesByMajorResponse
import com.capstone.compassionly.models.DetailMajorResponse
import com.capstone.compassionly.models.DetailUserModel
import com.capstone.compassionly.models.LoginResponse
import com.capstone.compassionly.models.MajorRecResponse
import com.capstone.compassionly.models.MajorResponse
import com.capstone.compassionly.models.RatingModel
import com.capstone.compassionly.models.SchoolMajor
import com.capstone.compassionly.models.SchoolModel
import com.capstone.compassionly.models.SuccessResponse
import com.capstone.compassionly.models.TopicModel
import com.capstone.compassionly.models.User
import com.capstone.compassionly.models.forsending.AccessToken
import com.capstone.compassionly.models.forsending.QuickRecResponse
import com.capstone.compassionly.models.forsending.RatingModelSend
import com.capstone.compassionly.models.forsending.UserDesc
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

    @GET("/api/v1/majors/majors")
    suspend fun getMajors(
        @HeaderMap headerMap: Map<String, String>,
    ): MajorResponse

    @GET("/api/v1/majors/majors/{major_id}")
    suspend fun getdetailMajor(
        @HeaderMap headerMap: Map<String, String>,
        @Path("major_id") majorId: Int
    ): DetailMajorResponse

    @GET("/api/v1/colleges/list-colleges-by-major/{major_id}")
    suspend fun getCollegesByMajor(
        @HeaderMap headerMap: Map<String, String>,
        @Path("major_id") majorId: Int
    ): CollegesByMajorResponse

    @POST("/api/v1/auth/logout")
    suspend fun logout(
        @Query("token") token: String
    ): Response<SuccessResponse<String>>

    @GET("/api/v1/topics/topics")
    suspend fun getAllTopic(
        @HeaderMap headerMap: Map<String, String>,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<SuccessResponse<List<TopicModel>>>

    @POST("/api/v1/topics/user-topic-rating")
    suspend fun postRating(
        @HeaderMap headerMap: Map<String, String>,
        @Body ratingModelSend: RatingModelSend
    ): Response<SuccessResponse<RatingModel>>

    @GET("/api/v1/topics/user-topic-rating/user-history")
    suspend fun getHistoryTopic(
        @HeaderMap headerMap: Map<String, String>,
    ): Response<SuccessResponse<List<RatingModel>>>

    @GET("/api/v1/topics/topics/{id}")
    suspend fun getTopicById(
        @HeaderMap headerMap: Map<String, String>,
        @Path("id") id: Int
    ): Response<SuccessResponse<TopicModel>>

    @GET("/api/v1/schools/list-schools")
    suspend fun getSchoolList(): Response<SuccessResponse<List<SchoolModel>>>

    @GET("/api/v1/schools/list-school-majors")
    suspend fun getSchoolMajorList(): Response<SuccessResponse<List<SchoolMajor>>>

    @GET("/api/v1/colleges/colleges")
    suspend fun getCollage(
        @HeaderMap headerMap: Map<String, String>
    ): Response<SuccessResponse<List<CollageModel>>>

    @GET("/api/v1/colleges/colleges/{id}")
    suspend fun getCollageDetail(
        @HeaderMap headerMap: Map<String, String>,
        @Path("id") id: Int
    ): Response<SuccessResponse<List<CollageModel>>>



    @POST("/api/v1/predict/quick-recommendation")
    suspend fun quickRecommendation(
        @HeaderMap headerMap: Map<String, String>,
        @Body text: UserDesc
    ): QuickRecResponse

    @POST("/api/v1/predict/major-recommendation")
    suspend fun majorRecommendation(
        @HeaderMap headerMap: Map<String, String>,
        @Body requestBody: Unit = Unit
    ): MajorRecResponse
}