package com.capstone.compassionly.datasource.preference.datasupport

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.capstone.compassionly.models.forsending.MajorRecResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStoreMajor: DataStore<Preferences> by preferencesDataStore("majorRec")

class MajorRecPreference(private val dataStore: DataStore<Preferences>) {
    suspend fun saveMajorRecResult(result: MajorRecResponse) {
        val predictionList: List<String?>? = result.data?.prediction
        val predictionString: String = predictionList?.joinToString(",") ?: ""

        dataStore.edit { preferences ->
            preferences[RESULT_MAJORREC] = predictionString
        }
    }

    fun getMajorRecResult(): Flow<List<String>> {
        return dataStore.data.map { preferences ->
            val predictionString: String = preferences[RESULT_MAJORREC] ?: ""
            val predictionList: List<String> = predictionString.split(",")
            predictionList
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: MajorRecPreference? = null

        private val RESULT_MAJORREC = stringPreferencesKey("result_majorrec")

        fun getInstance(dataStore: DataStore<Preferences>): MajorRecPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = MajorRecPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}