package com.capstone.compassionly.presentation.feature.quickrec.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.capstone.compassionly.models.DataItem
import com.capstone.compassionly.models.forsending.Data
import com.capstone.compassionly.models.forsending.QuickRecResponse
import com.capstone.compassionly.models.local.Interest
import com.capstone.compassionly.presentation.feature.show_recommendation.datadummy.Category
import com.capstone.compassionly.repository.core.network.QuickRecRepository
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

class QuickRecViewModel(
    private val quickRecRepository: QuickRecRepository,

) : ViewModel() {

    private val _interests = MutableLiveData<List<Interest>>()
    val interests: LiveData<List<Interest>> = _interests
    fun sendUserDesc(token: String, text: String) = quickRecRepository.sendUserDesc(token, text)

    fun saveQuickRecResult(result: QuickRecResponse) {
        Log.d("QuickRecVm", "save() $result")
        viewModelScope.launch {
            quickRecRepository.saveQuickRecResult(result)
        }
    }

    fun getQuickRecResult() {
        viewModelScope.launch {
            Log.d("QuickRecVM", "getQuickRecResult()")
            quickRecRepository.getQuickRecResult().collect { predictionList ->
                val interestList = predictionList.map { Interest(listOf(it)) }
                _interests.value = interestList
                Log.d("QuickRecVM", "predictionList: $predictionList")
                Log.d("QuickRecVM", "interestList: $interestList")
                Log.d("QuickRecVM", "interest: $_interests")
            }
        }
    }
}

