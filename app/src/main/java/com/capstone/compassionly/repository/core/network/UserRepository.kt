package com.capstone.compassionly.repository.core.network

import android.util.Log
import androidx.lifecycle.liveData
import com.capstone.compassionly.datasource.local.QuickRecPreference
import com.capstone.compassionly.datasource.network.ApiConfiguration.Companion.hitPointService
import com.capstone.compassionly.models.DetailUserModel
import com.capstone.compassionly.models.ErrorModel
import com.capstone.compassionly.models.ErrorUnDocumentedModel
import com.capstone.compassionly.models.SuccessResponse
import com.capstone.compassionly.models.User
import com.capstone.compassionly.models.forsending.AccessToken
import com.capstone.compassionly.models.forsending.Data
import com.capstone.compassionly.models.forsending.UserUpdateSend
import com.capstone.compassionly.utility.Resources
import com.capstone.compassionly.utility.Utils
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import retrofit2.Response

class UserRepository {

    suspend fun updateProfile(
        firstname: String,
        lastName: String,
        phoneNumber: String,
        gender: String,
        userSchoolId: Int,
        userSchoolMajorId: Int,
        token: String
    ): Response<SuccessResponse<User>> {
        return hitPointService.updatePersonalData(
            Utils.getHeader(token),
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

    suspend fun getMe(token: String): Response<SuccessResponse<DetailUserModel>> {
        return hitPointService.getMe(Utils.getHeader(token))
    }

    suspend fun removeTokenApi(token: String): Response<SuccessResponse<String>> {
        return hitPointService.logout(token)
    }

    fun sendToken(token: String) = liveData {
        emit(Resources.Loading)
        try {
            val accessToken = AccessToken(token)
            val response = hitPointService.accessToken(accessToken)
            Log.d("UserRepository", "$response")
            emit(Resources.Success(response))
        } catch (e: HttpException) {
            if (e.code() == 500) {
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, ErrorUnDocumentedModel::class.java)
                val errorMessage = errorBody.detail
                emit(Resources.Error(errorMessage!!))
            } else {
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, ErrorModel::class.java)
                val errorMessage = errorBody.detail
                emit(Resources.Error(errorMessage!!))
            }
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