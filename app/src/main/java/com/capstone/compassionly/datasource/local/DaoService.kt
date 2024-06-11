package com.capstone.compassionly.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.capstone.compassionly.models.local.LocalHistoryTopic
import com.capstone.compassionly.models.local.LocalUser
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoService {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserData(daoUser: LocalUser)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistory(localHistoryTopic: LocalHistoryTopic)

    @Query("DELETE FROM LOCALUSER")
    suspend fun deleteUser()

    @Query("SELECT * FROM LOCALUSER order by id ASC")
    fun getDataUser(): Flow<List<LocalUser>>

    @Query("SELECT * FROM LOCALHISTORYTOPIC WHERE userId == :id")
    fun getAllTopicHistory(id: Int): Flow<List<LocalHistoryTopic>>

    @Query("SELECT * FROM LOCALHISTORYTOPIC WHERE TOPICNAME LIKE :topic")
    fun searchTopicHistory(topic: String): Flow<List<LocalHistoryTopic>>

}