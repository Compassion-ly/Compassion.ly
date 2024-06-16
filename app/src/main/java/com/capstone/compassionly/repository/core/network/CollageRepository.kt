package com.capstone.compassionly.repository.core.network

import androidx.lifecycle.asLiveData
import com.capstone.compassionly.datasource.network.ApiConfiguration
import com.capstone.compassionly.datasource.preference.datasupport.StateAppPreference
import com.capstone.compassionly.utility.Utils

class CollageRepository(
    private val stateAppPreference: StateAppPreference,
    ) {

    suspend fun getAllCollage(token: String) = ApiConfiguration.hitPointService.getCollage(Utils.getHeader(token))

    suspend fun getCollageById(token: String, id: Int) = ApiConfiguration.hitPointService.getCollageById(Utils.getHeader(token), id)

    fun getToken() = stateAppPreference.getAccessToken().asLiveData()

    companion object {
        private var instance: CollageRepository? = null
        fun getInstance(stateAppPreference: StateAppPreference) = instance ?: synchronized(this) {
            instance = CollageRepository(stateAppPreference)
            instance
        }
    }
}