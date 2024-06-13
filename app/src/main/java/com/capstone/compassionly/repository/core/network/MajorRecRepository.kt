package com.capstone.compassionly.repository.core.network

import android.content.Context
import android.util.Log
import androidx.lifecycle.liveData
import com.capstone.compassionly.datasource.network.ApiConfiguration
import com.capstone.compassionly.datasource.preference.datasupport.MajorRecPreference
import com.capstone.compassionly.datasource.preference.datasupport.dataStoreMajor
import com.capstone.compassionly.models.ErrorMajorRecResponse
import com.capstone.compassionly.models.ErrorModel
import com.capstone.compassionly.models.MajorRecResponse
import com.capstone.compassionly.models.PredictionItem
import com.capstone.compassionly.utility.Resources
import com.capstone.compassionly.utility.Utils
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException

class MajorRecRepository(context: Context) {

    private val majorRecPreference = MajorRecPreference.getInstance(context.dataStoreMajor)

    fun requestMajorRecommendation(token: String) = liveData {
        emit(Resources.Loading)
        try {
            val response = ApiConfiguration.hitPointService.majorRecommendation(Utils.getHeader(token))
            Log.d("MajorrecRepo", "$response")
            emit(Resources.Success(response))
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorMajorRecResponse::class.java)
            val errorMessage = errorBody.detail
            emit(Resources.Error(errorMessage))
        } catch (e: Throwable) {
            emit(Resources.Error("Terjadi kesalahan lain."))
        }
    }


    fun getMajorRecResult(): Flow<List<PredictionItem>> {
        return majorRecPreference.getMajorRecResult()
    }

    suspend fun saveMajorRecResult(result: MajorRecResponse) {
        majorRecPreference.saveMajorRecResult(result)
    }

    companion object {
        const val TAG = "Major Rec Repository"

        @Volatile
        private var instance: MajorRecRepository? = null

        fun getInstance(
            context: Context
        ): MajorRecRepository =
            instance ?: synchronized(this) {
                instance ?: MajorRecRepository(context)
            }.also { instance = it }
    }

}