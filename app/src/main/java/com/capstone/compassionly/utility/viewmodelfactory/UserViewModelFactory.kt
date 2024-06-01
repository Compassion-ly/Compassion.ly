package com.capstone.compassionly.utility.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstone.compassionly.datasource.preference.datasupport.StateAppPreference
import com.capstone.compassionly.presentation.feature.users_data.view_model.UserViewModel
import com.capstone.compassionly.repository.core.network.UserRepository

class UserViewModelFactory(
    private val repo: UserRepository,
    private val state: StateAppPreference? = null
): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when (modelClass) {
            UserViewModel::class.java -> {
                return state?.let { UserViewModel(repo, it) } as T
            }
            else -> throw IllegalArgumentException("Class does't match")
        }
    }
}