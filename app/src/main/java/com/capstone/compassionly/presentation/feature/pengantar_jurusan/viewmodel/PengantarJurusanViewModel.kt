package com.capstone.compassionly.presentation.feature.pengantar_jurusan.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.compassionly.models.DataItem
import com.capstone.compassionly.repository.core.network.MajorRepository
import com.capstone.compassionly.utility.Resources

class PengantarJurusanViewModel(private val majorRepository: MajorRepository) : ViewModel() {

    private val _majors = MutableLiveData<List<DataItem>>()
    val majors: LiveData<List<DataItem>> = _majors

    private val _findMajor = MutableLiveData<List<DataItem>>()
    val findMajor: LiveData<List<DataItem>> = _findMajor

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getMajors(token: String) {
        majorRepository.getMajors(token).observeForever { resource ->
            when (resource) {
                is Resources.Success -> {
                    _majors.value = resource.data as List<DataItem>?
                    _isLoading.value = false
                }

                is Resources.Error -> {
                    Log.e(TAG, "${resource.error}")
                    _isLoading.value = false
                }

                is Resources.Loading -> {
                    _isLoading.value = true
                    Log.e(TAG, "Loading to get the major list...")

                }
            }
        }
    }

    fun getMajor(token: String, searchQuery: String) {
        majorRepository.getMajor(token,searchQuery).observeForever{ resource ->
            when (resource) {
                is Resources.Success -> {
                    val data = resource.data as? List<DataItem>
                    if (!data.isNullOrEmpty()) {
                        // Optionally, filter the data here if needed
                        _findMajor.value = data!!
                    } else {
                        Log.e(TAG, "No matching data found")
                        _findMajor.value = emptyList()
                    }
                    _isLoading.value = false
                }

                is Resources.Error -> {
                    Log.e(TAG, "error : ${resource.error}")
                    _isLoading.value = false
                }

                is Resources.Loading -> {
                    _isLoading.value = true
                    Log.d(TAG, "Loading to get the majors you are looking for")

                }
            }
        }

    }
    companion object {
        const val TAG = "pengantar jurusan view model"
    }
}