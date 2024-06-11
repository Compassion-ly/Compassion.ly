package com.capstone.compassionly.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.capstone.compassionly.models.local.LocalHistoryTopic
import com.capstone.compassionly.models.local.LocalUser

@TypeConverters(LocalUserConverter::class)
@Database(entities = [LocalHistoryTopic::class, LocalUser::class], version = 2, exportSchema = false)
abstract class DaoDatabase : RoomDatabase() {
    abstract fun daoService() : DaoService

    companion object {
        @Volatile
        private var instance : DaoDatabase? = null
        fun getInstance(context: Context) = instance ?: synchronized(this) {
            val instances = Room.databaseBuilder(
                context,
                DaoDatabase::class.java,
                "Compassionly"
            ).fallbackToDestructiveMigration().build()
            instance = instances
            instances
        }
    }

}