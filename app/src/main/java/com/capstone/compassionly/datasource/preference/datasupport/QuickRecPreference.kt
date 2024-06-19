package com.capstone.compassionly.datasource.preference.datasupport

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.capstone.compassionly.models.forsending.QuickRecResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "quickrecResult")

class QuickRecPreference (private val dataStore: DataStore<Preferences>) {

    suspend fun saveQuickRecResult(result: QuickRecResponse) {
        val predictionList: List<String?>? = result.data?.prediction
        val predictionString: String = predictionList?.joinToString(",") ?: ""

        dataStore.edit { preferences ->
            preferences[RESULT_QUICKREC] = predictionString
        }
    }

    fun getQuickRecResult(): Flow<List<String>> {
        return dataStore.data.map { preferences ->
            val predictionString: String = preferences[RESULT_QUICKREC] ?: ""
            val predictionList: List<String> = predictionString.split(",")
            predictionList
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: QuickRecPreference? = null

        private val RESULT_QUICKREC = stringPreferencesKey("result_quickrec")

        fun getInstance(dataStore: DataStore<Preferences>): QuickRecPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = QuickRecPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}
