package com.capstone.compassionly.presentation.feature.dashboard.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.capstone.compassionly.datasource.preference.datasupport.StateAppPreference
import com.capstone.compassionly.repository.core.local.LocalDataSource

class DashboardViewModel(
    private val localDataSource: LocalDataSource,
    private val stateAppPreferences: StateAppPreference
) : ViewModel() {

    fun getUserData() = localDataSource.getUser()

    fun getToken() = stateAppPreferences.getAccessToken().asLiveData()

}
