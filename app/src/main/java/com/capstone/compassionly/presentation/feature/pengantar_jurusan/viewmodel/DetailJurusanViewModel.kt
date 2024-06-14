package com.capstone.compassionly.presentation.feature.pengantar_jurusan.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.compassionly.models.Data
import com.capstone.compassionly.models.DataItemCollegesByMajor
import com.capstone.compassionly.repository.core.network.MajorRepository
import com.capstone.compassionly.utility.Resources

class DetailJurusanViewModel(private val majorRepository: MajorRepository) : ViewModel() {
    private val _colleges = MutableLiveData<List<DataItemCollegesByMajor>>()
    val colleges: LiveData<List<DataItemCollegesByMajor>> = _colleges

    private val _detailMajor = MutableLiveData<Data>()
    val detailMajor: LiveData<Data> = _detailMajor

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    //404
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

    fun getDetailMajor(token: String, majorId: Int) {
        majorRepository.getDetailMajor(token, majorId).observeForever { resource ->
            when (resource) {
                is Resources.Success -> {
                    _detailMajor.value = resource.data as Data?
                    _isLoading.value = false
                }

                is Resources.Error -> {
                    Log.e(TAG, "${resource.error}")
                    _isLoading.value = false
                }

                is Resources.Loading -> {
                    _isLoading.value = true
                    Log.e(TAG, "Loading to get the detail major...")

                }
            }
        }
    }

    companion object{
        const val TAG = "DetailJurusanVM"
    }
}