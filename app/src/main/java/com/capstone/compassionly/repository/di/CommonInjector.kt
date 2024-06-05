package com.capstone.compassionly.repository.di

import android.content.Context
import com.capstone.compassionly.datasource.local.DaoDatabase
import com.capstone.compassionly.datasource.preference.datasupport.StateAppPreference
import com.capstone.compassionly.datasource.preference.datasupport.datastore
import com.capstone.compassionly.repository.core.local.LocalDataSource
import com.capstone.compassionly.repository.core.network.MajorRepository
import com.capstone.compassionly.repository.core.network.SchoolRepository
import com.capstone.compassionly.repository.core.network.UserRepository
import com.capstone.compassionly.utility.viewmodelfactory.CommonViewModelFactory

object CommonInjector {

    fun dashboardInjector(context: Context) : CommonViewModelFactory {
        val userRep = UserRepository.getInstance()!!
        val schoolRep = SchoolRepository.getInstance()!!
        val state = StateAppPreference(context.datastore)
        val daoDatabase = DaoDatabase.getInstance(context)
        val localDataSource = LocalDataSource.getInstance(daoDatabase.daoService())
        val majorRep = MajorRepository.getInstance()
        return CommonViewModelFactory(userRep, schoolRep, localDataSource!!, state, majorRep)
    }

}