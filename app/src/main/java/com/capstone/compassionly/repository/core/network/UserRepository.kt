package com.capstone.compassionly.repository.core.network

import com.capstone.compassionly.datasource.network.ApiConfiguration
import com.capstone.compassionly.models.DetailUserModel
import com.capstone.compassionly.models.SchoolMajor
import com.capstone.compassionly.models.SchoolModel
import com.capstone.compassionly.models.SuccessResponse
import com.capstone.compassionly.models.UserModel
import com.capstone.compassionly.models.forsending.UserUpdateSend
import retrofit2.Response

class UserRepository {

    suspend fun updateProfile(
        firstname: String,
        lastName: String,
        phoneNumber: String,
        gender: String,
        userSchoolId: Int,
        userSchoolMajorId: Int,
    ): Response<SuccessResponse<UserModel>> {
        return ApiConfiguration.hitPointService.updatePersonalData(
            UserUpdateSend(
                firstName = firstname,
                lastName = lastName,
                phoneNumber = phoneNumber,
                gender = gender,
                schoolId = userSchoolId,
                schoolMajorId = userSchoolMajorId,
            )
        )
    }

    suspend fun getMe(): Response<SuccessResponse<DetailUserModel>> {
        return ApiConfiguration.hitPointService.getMe()
    }

    suspend fun removeTokenApi(token: String): Response<SuccessResponse<String>> {
        return ApiConfiguration.hitPointService.logout(token)
    }

    suspend fun getSchoolList(): Response<SuccessResponse<List<SchoolModel>>> {
        return ApiConfiguration.hitPointService.getSchoolList()
    }

    suspend fun getSchoolMajor(): Response<SuccessResponse<List<SchoolMajor>>> {
        return ApiConfiguration.hitPointService.getSchoolMajorList()
    }

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

        fun getInstance() = instance ?: synchronized(this) {
            instance = UserRepository()
            instance
        }
    }

}