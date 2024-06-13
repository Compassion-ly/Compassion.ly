package com.capstone.compassionly.repository.core.network

import android.content.Context
import com.capstone.compassionly.datasource.preference.datasupport.MajorRecPreference
import com.capstone.compassionly.datasource.preference.datasupport.QuickRecPreference
import com.capstone.compassionly.datasource.preference.datasupport.dataStore
import com.capstone.compassionly.datasource.preference.datasupport.dataStoreMajor
import com.capstone.compassionly.models.forsending.MajorRecResponse
import com.capstone.compassionly.models.forsending.QuickRecResponse
import kotlinx.coroutines.flow.Flow

class MajorRecRepository(context: Context) {

    private val majorRecPreference = MajorRecPreference.getInstance(context.dataStoreMajor)

    fun getMajorRecResult(): Flow<List<String>> {
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