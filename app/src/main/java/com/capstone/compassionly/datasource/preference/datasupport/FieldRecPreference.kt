package com.capstone.compassionly.datasource.preference.datasupport

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.capstone.compassionly.models.FieldRecResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


val Context.dataStoreField: DataStore<Preferences> by preferencesDataStore(name = "fieldrecResult")

class FieldRecPreference(private val dataStore: DataStore<Preferences>) {

    suspend fun saveFieldRecResult(result: FieldRecResponse) {
        val predictionList: List<String?>? = result.data?.topTopics
        val predictionString: String = predictionList?.joinToString(",") ?: ""

        dataStore.edit { preferences ->
            preferences[RESULT_QUICKREC] = predictionString
        }
    }

    fun getFieldRecResult(): Flow<List<String>> {
        return dataStore.data.map { preferences ->
            val predictionString: String = preferences[RESULT_QUICKREC] ?: ""
            val predictionList: List<String> = if (predictionString.isNotEmpty()) {
                predictionString.split(",").map { it.trim() }
            } else {
                emptyList()
            }
            predictionList
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: FieldRecPreference? = null

        private val RESULT_QUICKREC = stringPreferencesKey("result_fieldrec")

        fun getInstance(dataStore: DataStore<Preferences>): FieldRecPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = FieldRecPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}
