package com.capstone.compassionly.presentation.feature.pengantar_jurusan.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.compassionly.models.DataCourse
import com.capstone.compassionly.repository.core.network.MajorRepository
import com.capstone.compassionly.utility.Resources
import com.capstone.compassionly.utility.Utils

class DetailMatkulViewModel(private val majorRepository: MajorRepository) : ViewModel() {
    private val _detailMatkul = MutableLiveData<DataCourse>()
    val detailMatkul: LiveData<DataCourse> = _detailMatkul

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getDetailMatkul(token: String, matkulId: Int, context: Context) {
        majorRepository.getDetailCourse(token, matkulId).observeForever { resource ->
            when (resource) {
                is Resources.Success -> {
                    _detailMatkul.value = resource.data as DataCourse?
                    _isLoading.value = false
                }

                is Resources.Error -> {
                    Log.e(TAG, "${resource.error}")
                    _isLoading.value = false
                    Utils.showToast(context,"${resource.error}")
                }

                is Resources.Loading -> {
                    _isLoading.value = true
                    Log.e(TAG, "Loading to get the detail course...")

                }
            }
        }
    }

    companion object {
        const val TAG = "DetailMatkulVM"
    }
}