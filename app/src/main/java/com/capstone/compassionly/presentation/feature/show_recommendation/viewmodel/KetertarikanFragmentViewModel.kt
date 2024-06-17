package com.capstone.compassionly.presentation.feature.show_recommendation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.capstone.compassionly.datasource.preference.datasupport.StateAppPreference
import com.capstone.compassionly.models.FieldRecResponse
import com.capstone.compassionly.models.forsending.QuickRecResponse
import com.capstone.compassionly.models.local.Interest
import com.capstone.compassionly.repository.core.network.FieldRecRepository
import kotlinx.coroutines.launch

class KetertarikanFragmentViewModel(
    private val fieldRecRepository: FieldRecRepository,
    private val stateAppPreferences: StateAppPreference
) :
    ViewModel() {
    private val _interests = MutableLiveData<List<Interest>>()
    val interests: LiveData<List<Interest>> = _interests

    fun getToken() = stateAppPreferences.getAccessToken().asLiveData()

    fun saveQuickRecResult(result: FieldRecResponse) {
        Log.d("FieldRecVm", "save() $result")
        viewModelScope.launch {
            fieldRecRepository.saveFieldRecResult(result)
        }
    }

    fun getFieldRecResult() {
        viewModelScope.launch {
            Log.d("FieldRecVm", "getQuickRecResult()")
            fieldRecRepository.getFieldRecResult().collect { predictionList ->
                val interestList = predictionList.map { Interest(listOf(it)) }
                _interests.value = interestList
                Log.d("FieldRecVm", "predictionList: $predictionList")
                Log.d("FieldRecVm", "interestList: $interestList")
                Log.d("FieldRecVm", "interest: $_interests")
            }
        }
    }
}