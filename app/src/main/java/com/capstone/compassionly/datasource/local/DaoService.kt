package com.capstone.compassionly.datasource.local

import androidx.room.Dao
import androidx.room.Query
import com.capstone.compassionly.models.LocalHistoryTopic

@Dao
interface DaoService {

    @Query("SELECT * FROM LOCALHISTORYTOPIC")
    fun getAllTopicHistory(): List<LocalHistoryTopic>

}