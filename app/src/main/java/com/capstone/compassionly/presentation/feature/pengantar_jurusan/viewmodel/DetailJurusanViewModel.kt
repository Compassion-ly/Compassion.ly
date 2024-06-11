package com.capstone.compassionly.presentation.feature.pengantar_jurusan.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.compassionly.models.DataItemCollegesByMajor
import com.capstone.compassionly.repository.core.network.MajorRepository
import com.capstone.compassionly.utility.Resources

class DetailJurusanViewModel(private val majorRepository: MajorRepository) : ViewModel() {
    private val _colleges = MutableLiveData<List<DataItemCollegesByMajor>>()
    val colleges: LiveData<List<DataItemCollegesByMajor>> = _colleges

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getCollegesByMajor( token: String, majorId: Int) {
        majorRepository.getCollegesByMajor(token, majorId).observeForever { resource ->
            when (resource) {
                is Resources.Success -> {
                    _colleges.value = resource.data as List<DataItemCollegesByMajor>?
                    _isLoading.value = false
                }

                is Resources.Error -> {
                    Log.e(TAG, "${resource.error}")
                    _isLoading.value = false
                }

                is Resources.Loading -> {
                    _isLoading.value = true
                    Log.e(TAG, "Loading to get the colleges list...")

                }
            }
        }
    }

    companion object{
        const val TAG = "DetailJurusanVM"
    }
}