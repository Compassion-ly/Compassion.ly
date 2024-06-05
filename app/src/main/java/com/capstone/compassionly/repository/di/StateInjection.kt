package com.capstone.compassionly.repository.di

import android.content.Context
import com.capstone.compassionly.datasource.network.ApiConfiguration
import com.capstone.compassionly.datasource.network.HitPointService
import com.capstone.compassionly.datasource.preference.datasupport.StateAppPreference
import com.capstone.compassionly.datasource.preference.datasupport.datastore
import com.capstone.compassionly.repository.core.network.MajorRepository
import com.capstone.compassionly.repository.core.network.UserRepository
import com.capstone.compassionly.utility.viewmodelfactory.StateViewModelFactory
import com.capstone.compassionly.utility.viewmodelfactory.ViewModelFactory

object StateInjection {

    fun onBoardInjection(context: Context): StateViewModelFactory {
        val stateAppPreference = StateAppPreference(context.datastore)
        return StateViewModelFactory(stateAppPreference)
    }

    fun majorInjection(context: Context): ViewModelFactory {
        val hitPointService = ApiConfiguration.hitPointService
        val userRepository = UserRepository(hitPointService)
        val majorRepository = MajorRepository(hitPointService)
        return ViewModelFactory(userRepository, majorRepository)
    }

    fun provideRepository(): UserRepository {
        val hitPointService = ApiConfiguration.hitPointService
        return UserRepository.getInstance(hitPointService)
    }

    fun provideMajorRepository(): MajorRepository {
        val hitPointService = ApiConfiguration.hitPointService
        return MajorRepository.getInstance(hitPointService)
    }

}