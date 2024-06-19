package com.capstone.compassionly.presentation.feature.show_recommendation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.capstone.compassionly.datasource.preference.datasupport.StateAppPreference
import com.capstone.compassionly.models.PredictionItem
import com.capstone.compassionly.repository.core.network.MajorRecRepository
import kotlinx.coroutines.launch

class JurusanFragmentViewModel(
    private val majorRecRepository: MajorRecRepository,
    private val stateAppPreferences: StateAppPreference

    ) : ViewModel() {

    private val _majorrec = MutableLiveData<List<PredictionItem>>()
    val majorrec: LiveData<List<PredictionItem>> = _majorrec

    fun getMajorRecResult() {
        viewModelScope.launch {
            Log.d(TAG, "getMajorRecResult()")
            majorRecRepository.getMajorRecResult().collect { predictionList ->
                _majorrec.value = predictionList
                Log.d(TAG, "predictionList : $predictionList")
            }
        }
    }

    fun getToken() = stateAppPreferences.getAccessToken().asLiveData()


    companion object {
        const val TAG = "Jurusan Fragment VM"
    }
}