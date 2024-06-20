package com.capstone.compassionly.presentation.feature.topic.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.capstone.compassionly.datasource.preference.datasupport.StateAppPreference
import com.capstone.compassionly.models.ErrorModel
import com.capstone.compassionly.models.ErrorUnDocumentedModel
import com.capstone.compassionly.models.forsending.RatingModelSend
import com.capstone.compassionly.repository.core.local.LocalDataSource
import com.capstone.compassionly.repository.core.network.TopicRepository
import com.capstone.compassionly.utility.Resources
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class TopicViewModel(
    private val localDataSource: LocalDataSource,
    private val topicRepository: TopicRepository,
    private val stateAppPreference: StateAppPreference
) : ViewModel() {

    private var _resultPost: MutableLiveData<Resources<*>> = MutableLiveData()
    val resultPost : LiveData<Resources<*>> = _resultPost

    fun getTopic(token: String?, limit: Int, offSet: Int) = liveData {
        emit(Resources.Loading)
        token?.let {
            try {
                val call = topicRepository.getAllTopic(it, limit, offSet)
                if (call.isSuccessful) {
                    val body = call.body()?.data
                    emit(Resources.Success(body))
                } else if (call.code() == 404) {
                    emit(Resources.Error("Topik tidak ditemukan"))
                }
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
    }

    fun postToken(token: String?, rating: Int, topicId: Int) = viewModelScope.launch {
        token?.let {
            try {
                val rateModel = RatingModelSend(rating, topicId)
                val call = topicRepository.postRate(it, rateModel)
                if (call.isSuccessful) {
                    val body = call.body()
                    _resultPost.postValue(Resources.Success(body))
                } else {
                    _resultPost.postValue(Resources.Error("Terjadi kesalahan"))
                }
            } catch (e: HttpException) {
                if (e.code() == 500) {
                    val jsonInString = e.response()?.errorBody()?.string()
                    val errorBody = Gson().fromJson(jsonInString, ErrorUnDocumentedModel::class.java)
                    val errorMessage = errorBody.detail
                    _resultPost.postValue(Resources.Error(errorMessage))
                } else {
                    val jsonInString = e.response()?.errorBody()?.string()
                    val errorBody = Gson().fromJson(jsonInString, ErrorModel::class.java)
                    val errorMessage = errorBody.detail
                    _resultPost.postValue(Resources.Error(errorMessage))
                }
            }
        }
    }

    fun getToken() = stateAppPreference.getAccessToken().asLiveData()

    fun getUserData() = localDataSource.getUser()

    fun getTopicHistory(id : Int) = localDataSource.getHistoryTopic(id)


}