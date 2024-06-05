package com.capstone.compassionly.utility.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstone.compassionly.presentation.feature.login.viewmodel.LoginViewModel
import com.capstone.compassionly.presentation.feature.pengantar_jurusan.viewmodel.PengantarJurusanViewModel
import com.capstone.compassionly.repository.core.network.MajorRepository
import com.capstone.compassionly.repository.core.network.UserRepository
import com.capstone.compassionly.repository.di.StateInjection

class ViewModelFactory(
    private val userRepository: UserRepository,
    private val majorRepository: MajorRepository
) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(userRepository) as T
        }else if(modelClass.isAssignableFrom(PengantarJurusanViewModel::class.java)){
            return PengantarJurusanViewModel(majorRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    StateInjection.provideRepository(),
                    StateInjection.provideMajorRepository())
            }.also { instance = it }
    }
}