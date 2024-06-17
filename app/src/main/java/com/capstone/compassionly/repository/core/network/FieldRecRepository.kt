package com.capstone.compassionly.repository.core.network

import android.content.Context
import android.util.Log
import androidx.lifecycle.liveData
import com.capstone.compassionly.datasource.network.ApiConfiguration
import com.capstone.compassionly.datasource.preference.datasupport.FieldRecPreference
import com.capstone.compassionly.datasource.preference.datasupport.dataStoreField
import com.capstone.compassionly.models.ErrorMajorRecResponse
import com.capstone.compassionly.models.ErrorModel
import com.capstone.compassionly.models.FieldRecResponse
import com.capstone.compassionly.models.forsending.UserDesc
import com.capstone.compassionly.utility.Resources
import com.capstone.compassionly.utility.Utils
import com.google.gson.Gson

import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException


class FieldRecRepository(context: Context) {

    private val fieldRecPreference = FieldRecPreference.getInstance(context.dataStoreField)

    fun askRec(token: String) = liveData {
        emit(Resources.Loading)
        try {
            val response = ApiConfiguration.hitPointService.fieldRecommendation(Utils.getHeader(token))
            Log.d(TAG, "$response")
            emit(Resources.Success(response))
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorMajorRecResponse::class.java)
            val errorMessage = errorBody.detail
            emit(Resources.Error(errorMessage))
        }
    }

    fun getFieldRecResult(): Flow<List<String>> {
        return fieldRecPreference.getFieldRecResult()
    }

    suspend fun saveFieldRecResult(result: FieldRecResponse) {
        fieldRecPreference.saveFieldRecResult(result)
    }

    companion object {
        const val TAG = "Field Rec Repository"

        @Volatile
        private var instance: FieldRecRepository? = null

        fun getInstance(
            context: Context
        ): FieldRecRepository =
            instance ?: synchronized(this) {
                instance ?: FieldRecRepository(context)
            }.also { instance = it }
    }

}