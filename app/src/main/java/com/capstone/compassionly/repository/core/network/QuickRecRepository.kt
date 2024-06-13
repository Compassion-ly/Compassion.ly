package com.capstone.compassionly.repository.core.network

import android.content.Context
import android.util.Log
import androidx.lifecycle.liveData
import com.capstone.compassionly.datasource.preference.datasupport.QuickRecPreference
import com.capstone.compassionly.datasource.preference.datasupport.dataStore
import com.capstone.compassionly.datasource.network.ApiConfiguration
import com.capstone.compassionly.models.ErrorModel
import com.capstone.compassionly.models.forsending.QuickRecResponse
import com.capstone.compassionly.models.forsending.UserDesc
import com.capstone.compassionly.utility.Resources
import com.capstone.compassionly.utility.Utils
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException

class QuickRecRepository(context: Context) {

    private val quickRecPreference = QuickRecPreference.getInstance(context.dataStore)

    fun sendUserDesc(token: String, text: String) = liveData {
        emit(Resources.Loading)
        try {
            val textDesc = UserDesc(text)
            val response = ApiConfiguration.hitPointService.quickRecommendation(Utils.getHeader(token),textDesc)
            Log.d("QuickRecRepo", "$response")
            emit(Resources.Success(response))
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorModel::class.java)
            val errorMessage = errorBody.detail
            emit(Resources.Error(errorMessage))
        }
    }

    fun getQuickRecResult(): Flow<List<String>> {
        return quickRecPreference.getQuickRecResult()
    }

    suspend fun saveQuickRecResult(result: QuickRecResponse) {
        quickRecPreference.saveQuickRecResult(result)
    }

    companion object {
        const val TAG = "Quick Rec Repository"

        @Volatile
        private var instance: QuickRecRepository? = null

        fun getInstance(context: Context
        ): QuickRecRepository =
            instance ?: synchronized(this) {
                instance ?: QuickRecRepository(context)
            }.also { instance = it }
    }

}