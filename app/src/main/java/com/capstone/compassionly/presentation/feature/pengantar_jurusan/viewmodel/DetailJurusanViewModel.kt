package com.capstone.compassionly.presentation.feature.pengantar_jurusan.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.compassionly.models.Data
import com.capstone.compassionly.repository.core.network.MajorRepository
import com.capstone.compassionly.utility.Resources
import com.capstone.compassionly.utility.Utils

class DetailJurusanViewModel(private val majorRepository: MajorRepository) : ViewModel() {

    private val _detailMajor = MutableLiveData<Data>()
    val detailMajor: LiveData<Data> = _detailMajor

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    fun getDetailMajor(token: String, majorId: Int, context: Context) {
        majorRepository.getDetailMajor(token, majorId).observeForever { resource ->
            when (resource) {
                is Resources.Success -> {
                    _detailMajor.value = resource.data as Data?
                    _isLoading.value = false
                }

                is Resources.Error -> {
                    Log.e(TAG, "${resource.error}")
                    _isLoading.value = false
                    Utils.showToast(context,"${resource.error}")
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