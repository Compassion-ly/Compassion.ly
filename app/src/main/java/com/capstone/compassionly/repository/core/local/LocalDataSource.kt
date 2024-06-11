package com.capstone.compassionly.repository.core.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.capstone.compassionly.datasource.local.DaoService
import com.capstone.compassionly.models.local.LocalHistoryTopic
import com.capstone.compassionly.models.local.LocalUser

class LocalDataSource (
    private val daoService: DaoService
) {

    suspend fun insertUser(localUser: LocalUser?) {
        return daoService.insertUserData(localUser!!)
    }

    suspend fun insertHistory(localHistoryTopic: LocalHistoryTopic) {
        return daoService.insertHistory(localHistoryTopic)
    }

    suspend fun deleteUser() {
        return daoService.deleteUser()
    }

    fun getUser() : LiveData<List<LocalUser>> {
        return daoService.getDataUser().asLiveData()
    }

    fun getHistoryTopic(id: Int) : LiveData<List<LocalHistoryTopic>> {
        return daoService.getAllTopicHistory(id).asLiveData()
    }

    fun getHistoryWithTopic(search: String) : LiveData<List<LocalHistoryTopic>> {
        val query = "%$search%"
        return daoService.searchTopicHistory(query).asLiveData()
    }

    companion object {
        @Volatile
        private var instance: LocalDataSource? = null
        fun getInstance(daoService: DaoService) = instance ?: synchronized(this) {
            instance = LocalDataSource(daoService)
            instance
        }
    }

}