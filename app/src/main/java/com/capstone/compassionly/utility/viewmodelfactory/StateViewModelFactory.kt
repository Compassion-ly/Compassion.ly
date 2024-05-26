package com.capstone.compassionly.utility.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstone.compassionly.presentation.feature.onboarding.viewmodel.OnBoardViewModel
import com.capstone.compassionly.utility.datasupport.StateAppPreference

class StateViewModelFactory(
    private val state : StateAppPreference?
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (state != null) {
            when (modelClass) {
                OnBoardViewModel::class.java -> {
                    return OnBoardViewModel(state) as T
                }
                else -> throw IllegalArgumentException("Class don't match")
            }
        } else {
            throw IllegalArgumentException("Argument state must be attached")
        }
    }

}