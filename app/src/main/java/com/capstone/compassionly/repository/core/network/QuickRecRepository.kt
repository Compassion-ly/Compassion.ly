package com.capstone.compassionly.repository.core.network

import android.util.Log
import androidx.lifecycle.liveData
import com.capstone.compassionly.datasource.network.ApiConfiguration
import com.capstone.compassionly.models.ErrorModel
import com.capstone.compassionly.models.forsending.UserDesc
import com.capstone.compassionly.utility.Resources
import com.capstone.compassionly.utility.Utils
import com.google.gson.Gson
import retrofit2.HttpException

class QuickRecRepository {

    fun sendUserDesc(token: String, text: String) = liveData {
        emit(Resources.Loading)
        try {
            val text = UserDesc(text)
            val response = ApiConfiguration.hitPointService.quickRecommendation(Utils.getHeader(token), text)
            Log.d("QuickRecRepo", "$response")
            emit(Resources.Success(response))
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorModel::class.java)
            val errorMessage = errorBody.detail
            emit(Resources.Error(errorMessage))
        }
    }

    companion object {
        const val TAG = "Quick Rec Repository"

        @Volatile
        private var instance: QuickRecRepository? = null

        fun getInstance(
        ): QuickRecRepository =
            instance ?: synchronized(this) {
                instance ?: QuickRecRepository()
            }.also { instance = it }
    }

}