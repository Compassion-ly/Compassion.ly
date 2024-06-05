package com.capstone.compassionly.repository.di

import android.content.Context
import com.capstone.compassionly.datasource.preference.datasupport.StateAppPreference
import com.capstone.compassionly.datasource.preference.datasupport.datastore
import com.capstone.compassionly.repository.core.network.MajorRepository
import com.capstone.compassionly.repository.core.network.UserRepository
import com.capstone.compassionly.utility.viewmodelfactory.StateViewModelFactory

object StateInjection {

    fun onBoardInjection(context: Context): StateViewModelFactory {
        val stateAppPreference = StateAppPreference(context.datastore)
        return StateViewModelFactory(stateAppPreference)
    }

    fun provideRepository(): UserRepository? {
        return UserRepository.getInstance()
    }

    fun provideMajorRepository(): MajorRepository {
        return MajorRepository.getInstance()
    }
}