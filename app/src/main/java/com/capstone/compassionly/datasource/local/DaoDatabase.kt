package com.capstone.compassionly.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.capstone.compassionly.models.LocalHistoryTopic

@Database(entities = [LocalHistoryTopic::class], version = 1, exportSchema = false)
abstract class DaoDatabase : RoomDatabase() {
    abstract fun daoService() : DaoService

    companion object {
        private var instance : DaoDatabase? = null
        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: Room.databaseBuilder(
                context,
                DaoDatabase::class.java,
                "LocalHistoryTopic"
            ).fallbackToDestructiveMigration().build().also { instance = it }
        }
    }
}