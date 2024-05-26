package com.capstone.compassionly.repository.di

import android.content.Context
import com.capstone.compassionly.utility.datasupport.StateAppPreference
import com.capstone.compassionly.utility.datasupport.datastore
import com.capstone.compassionly.utility.viewmodelfactory.StateViewModelFactory

object StateInjection {

    fun onBoardInjection(context : Context) : StateViewModelFactory {
        val stateAppPreference = StateAppPreference(context.datastore)
        return StateViewModelFactory(stateAppPreference)
    }

}