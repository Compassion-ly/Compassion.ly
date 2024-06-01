package com.capstone.compassionly.repository.di

import android.content.Context
import com.capstone.compassionly.datasource.preference.datasupport.StateAppPreference
import com.capstone.compassionly.datasource.preference.datasupport.datastore
import com.capstone.compassionly.repository.core.network.UserRepository
import com.capstone.compassionly.utility.viewmodelfactory.UserViewModelFactory

object UserInjector {

    fun userInjector(context : Context) : UserViewModelFactory {
        val userRep = UserRepository.getInstance()!!
        val state = StateAppPreference(context.datastore)
        return UserViewModelFactory(userRep, state)
    }


}