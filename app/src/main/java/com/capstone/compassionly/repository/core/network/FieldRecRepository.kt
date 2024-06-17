package com.capstone.compassionly.repository.core.network

import android.content.Context
import com.capstone.compassionly.datasource.preference.datasupport.FieldRecPreference
import com.capstone.compassionly.datasource.preference.datasupport.dataStoreField
import com.capstone.compassionly.models.FieldRecResponse

import kotlinx.coroutines.flow.Flow


class FieldRecRepository(context: Context) {

    private val fieldRecPreference = FieldRecPreference.getInstance(context.dataStoreField)


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