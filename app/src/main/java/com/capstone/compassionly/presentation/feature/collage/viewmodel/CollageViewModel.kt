package com.capstone.compassionly.presentation.feature.collage.viewmodel

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.capstone.compassionly.models.ErrorModel
import com.capstone.compassionly.models.ErrorUnDocumentedModel
import com.capstone.compassionly.repository.core.network.CollageRepository
import com.capstone.compassionly.utility.Resources
import com.capstone.compassionly.utility.Utils
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class CollageViewModel(
    private val collageRepository: CollageRepository,
) : ViewModel() {

    private val _result: MutableLiveData<Resources<*>> = MutableLiveData<Resources<*>>()
    val result get() = _result

    private val _detailCollage: MutableLiveData<Resources<*>> = MutableLiveData<Resources<*>>()
    val detailCollage get() = _detailCollage

    private fun getAllCollage(context: Context, token: String) = liveData {
        emit(Resources.Loading)
        if (!Utils.checkConnection(context)) {
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
                    val errorBody =
                        Gson().fromJson(jsonInString, ErrorUnDocumentedModel::class.java)
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

    fun getDetailCollage(context: Context, token: String, id: Int) = liveData {
        emit(Resources.Loading)
        if (!Utils.checkConnection(context)) {
            emit(Resources.Error("Tidak ada koneksi internet, harap coba lagi"))
        } else {
            try {
                val call = token.let { collageRepository.getCollageById(it, id)}
                if (call.isSuccessful) {
                    val body = call.body()?.data
                    body?.let {
                        emit(Resources.Success(it))
                    } ?: run {
                        emit(Resources.Error("Data tidak ada"))
                    }
                }
            } catch (e: HttpException) {
                if (e.code() == 500) {
                    val jsonInString = e.response()?.errorBody()?.string()
                    val errorBody =
                        Gson().fromJson(jsonInString, ErrorUnDocumentedModel::class.java)
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
        collageRepository.getToken().observe(lifecycleOwner) { token ->
            token?.let {
                getAllCollage(context, it).observe(lifecycleOwner) { res ->
                    when (res) {
                        is Resources.Error -> {
                            _result.postValue(res)
                        }

                        is Resources.Success -> {
                            _result.postValue(res)
                        }

                        is Resources.Loading -> {
                            _result.postValue(res)
                        }
                    }
                }
            } ?: run {
                _result.postValue(Resources.Error("Token invalid"))
            }
        }
    }

    fun getDetailCollageById(token: String, collageId: Int) = viewModelScope.launch {
        try {
            val call = collageRepository.getCollageByIdDetail(token, collageId)
            if (call.isSuccessful) {
                val listOfMajor = call.body()?.data?.majors
                listOfMajor?.let {  listMajor ->
                    _detailCollage.postValue(Resources.Success(listMajor))
                } ?: run {
                    _detailCollage.postValue(Resources.Error("Data Kosong"))
                }
            }
        } catch (e: HttpException) {
            if (e.code() == 500) {
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody =
                    Gson().fromJson(jsonInString, ErrorUnDocumentedModel::class.java)
                val errorMessage = errorBody.detail
                _detailCollage.postValue(Resources.Error(errorMessage!!))
            } else {
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, ErrorModel::class.java)
                val errorMessage = errorBody.detail
                _detailCollage.postValue(Resources.Error(errorMessage!!))
            }
        }
    }

    fun getAllDetail(context: Context, token: String) = liveData {
        emit(Resources.Loading)
        if (!Utils.checkConnection(context)) {
            emit(Resources.Error("Tidak ada koneksi internet, harap coba lagi"))
        } else {
            try {
                val call = token.let { collageRepository.getCollegeDetail(it)}
                if (call.isSuccessful) {
                    val body = call.body()?.data
                    body?.let {
                        emit(Resources.Success(it))
                    } ?: run {
                        emit(Resources.Error("Data tidak ada"))
                    }
                }
            } catch (e: HttpException) {
                if (e.code() == 500) {
                    val jsonInString = e.response()?.errorBody()?.string()
                    val errorBody =
                        Gson().fromJson(jsonInString, ErrorUnDocumentedModel::class.java)
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

    fun getToken() = collageRepository.getToken()
}