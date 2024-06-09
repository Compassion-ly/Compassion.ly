package com.capstone.compassionly.utility.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstone.compassionly.datasource.preference.datasupport.StateAppPreference
import com.capstone.compassionly.presentation.feature.dashboard.viewmodel.DashboardViewModel
import com.capstone.compassionly.presentation.feature.login.viewmodel.LoginViewModel
import com.capstone.compassionly.presentation.feature.pengantar_jurusan.viewmodel.DetailJurusanViewModel
import com.capstone.compassionly.presentation.feature.pengantar_jurusan.viewmodel.PengantarJurusanViewModel
import com.capstone.compassionly.presentation.feature.users_data.view_model.UserViewModel
import com.capstone.compassionly.repository.core.local.LocalDataSource
import com.capstone.compassionly.repository.core.network.MajorRepository
import com.capstone.compassionly.repository.core.network.SchoolRepository
import com.capstone.compassionly.repository.core.network.UserRepository

class CommonViewModelFactory(
    private val repo: UserRepository,
    private val schoolRepository: SchoolRepository,
    private val localDataSource: LocalDataSource,
    private val state: StateAppPreference,
    private val majorRepository: MajorRepository

): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            UserViewModel::class.java -> {
                UserViewModel(repo, schoolRepository, state, localDataSource) as T
            }

            LoginViewModel::class.java -> {
                LoginViewModel(repo, localDataSource) as T
            }

            DashboardViewModel::class.java -> {
                DashboardViewModel(localDataSource, state) as T
            }

            PengantarJurusanViewModel::class.java-> {
                PengantarJurusanViewModel(majorRepository) as T
            }

            DetailJurusanViewModel::class.java-> {
                DetailJurusanViewModel(majorRepository) as T
            }

            else -> throw IllegalArgumentException("Class does't match")
        }
    }
}