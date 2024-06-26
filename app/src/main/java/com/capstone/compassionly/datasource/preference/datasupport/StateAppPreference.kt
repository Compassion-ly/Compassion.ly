package com.capstone.compassionly.datasource.preference.datasupport

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.datastore : DataStore<Preferences> by preferencesDataStore("state")
class StateAppPreference(val dataStore: DataStore<Preferences>) {

    object ModelState {
        val onBoardState = stringPreferencesKey("onBoardState")
        val accessToken = stringPreferencesKey("access_token")
    }

    suspend fun updateOnBoardState() {
        dataStore.edit { preference ->
            preference[ModelState.onBoardState] = "Done"
        }
    }

    fun getOnBoardState() : Flow<String?> {
        return dataStore.data.map {
            it[ModelState.onBoardState]
        }
    }

    fun getAccessToken() : Flow<String?> {
        return dataStore.data.map {
            it[ModelState.accessToken]
        }
    }

    suspend fun setAccessToken(accessToken: String) {
        dataStore.edit {
            it[ModelState.accessToken] = accessToken
        }
    }

    suspend fun deleteAccessToken() {
        dataStore.edit {
            it[ModelState.accessToken] = ""
        }
    }

}