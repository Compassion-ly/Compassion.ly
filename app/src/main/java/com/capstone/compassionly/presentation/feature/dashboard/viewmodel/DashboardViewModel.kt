package com.capstone.compassionly.presentation.feature.dashboard.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.capstone.compassionly.datasource.preference.datasupport.StateAppPreference
import com.capstone.compassionly.models.ErrorModel
import com.capstone.compassionly.models.ErrorUnDocumentedModel
import com.capstone.compassionly.models.local.LocalHistoryTopic
import com.capstone.compassionly.repository.core.local.LocalDataSource
import com.capstone.compassionly.repository.core.network.UserRepository
import com.capstone.compassionly.utility.Utils
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class DashboardViewModel(
    private val localDataSource: LocalDataSource,
    private val stateAppPreferences: StateAppPreference,
    private val userRepository: UserRepository
) : ViewModel() {

    fun getUserData() = localDataSource.getUser()

    fun getToken() = stateAppPreferences.getAccessToken().asLiveData()

    fun updateUserHistory(context: Context, token: String) = viewModelScope.launch {
        if (Utils.checkConnection(context)) {
            try {
                val call = userRepository.getUserHistoryTopic(token)
                if (call.isSuccessful) {
                    val body = call.body()?.data
                    body?.forEach {
                        val topicName = userRepository.getUserTopicById(token, it.topicId!!).body()?.data?.topicName
                        val createMockLocalTopicHistory = LocalHistoryTopic(
                            id = it.id,
                            userId = it.userId,
                            rating = it.rating,
                            topicId = it.topicId,
                            topicName = topicName
                        )
                        localDataSource.insertHistory(createMockLocalTopicHistory)
                    }
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
    }

}
