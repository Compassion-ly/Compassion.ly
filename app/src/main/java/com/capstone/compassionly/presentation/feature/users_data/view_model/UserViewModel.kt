package com.capstone.compassionly.presentation.feature.users_data.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.capstone.compassionly.datasource.preference.datasupport.StateAppPreference
import com.capstone.compassionly.models.DetailUserModel
import com.capstone.compassionly.models.ErrorModel
import com.capstone.compassionly.models.ErrorUnDocumentedModel
import com.capstone.compassionly.models.SchoolMajor
import com.capstone.compassionly.models.SchoolModel
import com.capstone.compassionly.models.SuccessResponse
import com.capstone.compassionly.models.local.LocalUser
import com.capstone.compassionly.repository.core.local.LocalDataSource
import com.capstone.compassionly.repository.core.network.SchoolRepository
import com.capstone.compassionly.repository.core.network.UserRepository
import com.capstone.compassionly.utility.Resources
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class UserViewModel(
    private val userRepository: UserRepository,
    private val schoolRepository: SchoolRepository,
    private val stateAppPreference: StateAppPreference,
    private val localDataSource: LocalDataSource
) : ViewModel() {

    fun update(
        firstName: String,
        lastName: String,
        phoneNumber: String,
        gender: String,
        userSchoolId: Int,
        userSchoolMajorId: Int,
        token: String,
    ) = liveData {
        emit(Resources.Loading)
        try {
            val response = userRepository.updateProfile(
                firstName,
                lastName,
                phoneNumber,
                gender,
                userSchoolId,
                userSchoolMajorId,
                token
            )
            if (response.isSuccessful) {
                response.body().apply {
                    emit(Resources.Success(this))
                }
            }
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorModel::class.java)
            val errorMessage = errorBody.detail
            emit(Resources.Error(errorMessage!!))
        }
    }

    fun removeTokenApi(token: String): LiveData<Resources<SuccessResponse<String>?>> {
        val result = MutableLiveData<Resources<SuccessResponse<String>?>>()
        viewModelScope.launch {
            val response = userRepository.removeTokenApi(token)
            if (response.isSuccessful) {
                result.postValue(
                    Resources.Success(response.body())
                )
            }
        }
        return result
    }

    fun getSchoolList(): LiveData<List<SchoolModel>?> {
        val result = MutableLiveData<List<SchoolModel>?>()
        viewModelScope.launch {
            schoolRepository.getSchoolList().apply {
                if (this.isSuccessful) {
                    result.postValue(this.body()?.data)
                }
            }
        }
        return result
    }

    fun getSchoolMajorList(): LiveData<List<SchoolMajor>?> {
        val result = MutableLiveData<List<SchoolMajor>?>()
        viewModelScope.launch {
            schoolRepository.getSchoolMajor().apply {
                if (this.isSuccessful) {
                    result.postValue(this.body()?.data)
                }
            }
        }
        return result
    }

    fun getMe(token: String) = liveData {
        emit(Resources.Loading)
        try {
            val response = userRepository.getMe(token)
            if (response.isSuccessful) {
                response.body().apply {
                    emit(Resources.Success(this))
                }
            }
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorModel::class.java)
            emit(Resources.Error(errorBody))
        }

    }

    fun updateToken(token: String) = viewModelScope.launch {
        try {
            val call = userRepository.updateToken(token)
            if (call.data != null) {
                call.data.accessToken?.let { storeToken(it) }
            }
        } catch (e: HttpException) {
            if (e.code() == 500) {
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody =
                    Gson().fromJson(jsonInString, ErrorUnDocumentedModel::class.java)
                val errorMessage = errorBody.detail
            } else {
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, ErrorModel::class.java)
                val errorMessage = errorBody.detail
            }
        }
    }

    // Local and State

    fun store(localUser: LocalUser?) = viewModelScope.launch {
        localDataSource.insertUser(localUser)
    }

    fun deleteUser() = viewModelScope.launch {
        localDataSource.deleteUser()
    }

    fun getDataUser() = localDataSource.getUser()

    fun storeToken(token: String) = viewModelScope.launch {
        stateAppPreference.setAccessToken(token)
    }

    fun getAccessToken(): LiveData<String?> {
        return stateAppPreference.getAccessToken().asLiveData()
    }

    fun removeAccessToken() = viewModelScope.launch {
        stateAppPreference.deleteAccessToken()
    }

}