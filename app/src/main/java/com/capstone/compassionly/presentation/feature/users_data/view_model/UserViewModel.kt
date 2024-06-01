package com.capstone.compassionly.presentation.feature.users_data.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.capstone.compassionly.datasource.preference.datasupport.StateAppPreference
import com.capstone.compassionly.models.DetailUserModel
import com.capstone.compassionly.models.SchoolMajor
import com.capstone.compassionly.models.SchoolModel
import com.capstone.compassionly.models.SuccessResponse
import com.capstone.compassionly.models.UserModel
import com.capstone.compassionly.repository.core.network.UserRepository
import com.capstone.compassionly.utility.ResourcesResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException

class UserViewModel(
    private val userRepository: UserRepository,
    private val stateAppPreference: StateAppPreference
) : ViewModel() {

    // UPDATE
    private var _updateResponse: MutableLiveData<ResourcesResponse<SuccessResponse<UserModel>>?> = MutableLiveData()
    private val updateResponse get() = _updateResponse

    // ME
    private var _meResponse: MutableLiveData<ResourcesResponse<SuccessResponse<DetailUserModel>?>> = MutableLiveData()
    private val meResponse get()  = _meResponse


    // CALL FUNCTION
    fun update(
        firstName: String,
        lastName: String,
        phoneNumber: String,
        gender: String,
        userSchoolId: Int,
        userSchoolMajorId: Int,
    ) : LiveData<ResourcesResponse<SuccessResponse<UserModel>>?> {
        viewModelScope.launch {
            _updateResponse.postValue(ResourcesResponse.Loading())
            processUpdate(firstName, lastName, phoneNumber, gender, userSchoolId, userSchoolMajorId)
        }
        return updateResponse
    }

    fun getMe() : LiveData<ResourcesResponse<SuccessResponse<DetailUserModel>?>> {
        viewModelScope.launch {
            _meResponse.postValue(ResourcesResponse.Loading())
            processMe()
        }
        return meResponse
    }

    fun removeTokenApi(token: String) : LiveData<SuccessResponse<String>> {
        val result = MutableLiveData<SuccessResponse<String>>()
        viewModelScope.launch {
            val response = userRepository.removeTokenApi(token)
            if (response.isSuccessful) {
                result.postValue(response.body())
            }
        }
        return result
    }

    fun getSchoolList() : LiveData<List<SchoolModel>?> {
        val result = MutableLiveData<List<SchoolModel>?>()
        viewModelScope.launch {
            userRepository.getSchoolList().apply {
                if (this.isSuccessful) {
                    result.postValue(this.body()?.data)
                }
            }
        }
        return result
    }

    fun getSchoolMajorList() : LiveData<List<SchoolMajor>?> {
        val result = MutableLiveData<List<SchoolMajor>?>()
        viewModelScope.launch {
            userRepository.getSchoolMajor().apply {
                if (this.isSuccessful) {
                    result.postValue(this.body()?.data)
                }
            }
        }
        return result
    }

    fun getAccessToken(): LiveData<String?> {
        return stateAppPreference.getAccessToken().asLiveData()
    }

    fun storeAccessToken(access: String) = viewModelScope.launch {
        stateAppPreference.setAccessToken(access)
    }

    fun removeAccessToken() = viewModelScope.launch {
        stateAppPreference.deleteAccessToken()
    }


    // PROCESS SIDE
    private suspend fun processUpdate(
        firstName: String,
        lastName: String,
        phoneNumber: String,
        gender: String,
        userSchoolId: Int,
        userSchoolMajorId: Int,
    ) {
        try {
            val updateCaller = userRepository.updateProfile(
                firstName, lastName, phoneNumber, gender, userSchoolId, userSchoolMajorId
            )
            if (updateCaller.isSuccessful) {
                // Use mock response
                updateCaller.body().let {
                    if (it != null) {
                        ResourcesResponse.OnSuccess(it).also { resValue ->
                            _updateResponse.postValue(resValue)
                        }
                    }
                }
            } else {
                _updateResponse.postValue(ResourcesResponse.OnFailure("Something went wrong"))
            }
        } catch (e: HttpException) {
            throw e.fillInStackTrace()
        }

    }

    private suspend fun processMe() {
        try {
            val meCall = userRepository.getMe()
            if (meCall.isSuccessful) {
                meCall.body().also {
                    ResourcesResponse.OnSuccess(it).also { value ->
                        _meResponse.postValue(value)
                    }
                }
            } else {
                _meResponse.postValue(ResourcesResponse.OnFailure("Something went wrong"))
            }
        } catch (e: HttpException) {
            throw e.fillInStackTrace()
        }
    }

}