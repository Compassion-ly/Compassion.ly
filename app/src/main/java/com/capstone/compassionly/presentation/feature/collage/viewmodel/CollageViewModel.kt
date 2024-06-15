package com.capstone.compassionly.presentation.feature.collage.viewmodel

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.capstone.compassionly.datasource.preference.datasupport.StateAppPreference
import com.capstone.compassionly.models.ErrorModel
import com.capstone.compassionly.models.ErrorUnDocumentedModel
import com.capstone.compassionly.repository.core.local.LocalDataSource
import com.capstone.compassionly.repository.core.network.CollageRepository
import com.capstone.compassionly.utility.Resources
import com.capstone.compassionly.utility.Utils
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class CollageViewModel(
    private val collageRepository: CollageRepository,
): ViewModel() {

    private val _result : MutableLiveData<Resources<*>> = MutableLiveData<Resources<*>>()
    val result get() = _result

    fun getAllCollage(context: Context, token: String) = liveData {
        emit(Resources.Loading)
        if (Utils.checkConnection(context)) {
            emit(Resources.Error("Tidak ada koneksi internet, harap coba lagi"))
        } else {
           try {
               val call = collageRepository.getAllCollage(token)
               if (call.isSuccessful) {
                   val data = call.body()?.data
                   data?.let {
                       emit(Resources.Success(data))
                   } ?: run {
                       emit(Resources.Error("Data kosong"))
                   }
               }
           } catch (e: HttpException) {
               if (e.code() == 500) {
                   val jsonInString = e.response()?.errorBody()?.string()
                   val errorBody = Gson().fromJson(jsonInString, ErrorUnDocumentedModel::class.java)
                   val errorMessage = errorBody.detail
                   emit(Resources.Error(errorMessage!!))
               } else {
                   val jsonInString = e.response()?.errorBody()?.string()
                   val errorBody = Gson().fromJson(jsonInString, ErrorModel::class.java)
                   val errorMessage = errorBody.detail
                   emit(Resources.Error(errorMessage!!))
               }
           }
        }
    }

    fun getCollage(lifecycleOwner: LifecycleOwner, context: Context) = viewModelScope.launch {
        collageRepository.getToken().observe(lifecycleOwner) {
            it?.let {
                getAllCollage(context, it).observe(lifecycleOwner) {
                    when (it) {
                        is Resources.Error -> {
                            _result.postValue(it)
                        }
                        is Resources.Success -> {
                            _result.postValue(it)
                        }
                        is Resources.Loading -> {
                            _result.postValue(it)
                        }
                    }
                }
            } ?: run {
                _result.postValue(Resources.Error("Token invalid"))
            }
        }
    }

}