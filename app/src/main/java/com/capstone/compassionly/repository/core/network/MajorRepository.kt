package com.capstone.compassionly.repository.core.network

import android.util.Log
import androidx.lifecycle.liveData
import com.capstone.compassionly.datasource.network.ApiConfiguration.Companion.hitPointService
import com.capstone.compassionly.models.ErrorModel
import com.capstone.compassionly.utility.Resources
import com.capstone.compassionly.utility.Utils
import com.google.gson.Gson
import retrofit2.HttpException

class MajorRepository {

    fun getMajors(token: String) = liveData {
        emit(Resources.Loading)
        try {
            val response = hitPointService.getMajors(Utils.getHeader(token))
            response.data?.let {
                emit(Resources.Success(it.filterNotNull()))
            } ?: emit(Resources.Error("No data available"))
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorModel::class.java)
            val errorMessage = errorBody.detail
            emit(Resources.Error(errorMessage))
        }
    }

    fun getMajor(token: String, searchQuery: String) = liveData {
        emit(Resources.Loading)
        try {
            val response = hitPointService.getMajor(Utils.getHeader(token), searchQuery)
            val data = response.data
            if (!data.isNullOrEmpty()) {
                emit(Resources.Success(data))
            } else {
                Log.e(TAG, "Data not found")
                emit(Resources.Error("Data not found"))
            }
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorModel::class.java)
            val errorMessage = errorBody.detail
            emit(Resources.Error(errorMessage))
        }
    }


    companion object {
        const val TAG = "Major Repository"

        @Volatile
        private var instance: MajorRepository? = null

        fun getInstance(
        ): MajorRepository =
            instance ?: synchronized(this) {
                instance ?: MajorRepository()
            }.also { instance = it }
    }

}