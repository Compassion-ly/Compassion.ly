package com.capstone.compassionly.presentation.feature.onboarding.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.capstone.compassionly.datasource.preference.datasupport.StateAppPreference
import kotlinx.coroutines.launch

class OnBoardViewModel(
    private val state: StateAppPreference
) : ViewModel() {

    fun getOnBoardState() : LiveData<String?> {
        return state.getOnBoardState().asLiveData()
    }

    fun updateOnBoardState() = viewModelScope.launch {
        state.updateOnBoardState()
    }

}