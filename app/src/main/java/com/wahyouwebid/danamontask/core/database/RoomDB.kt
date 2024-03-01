package com.wahyouwebid.danamontask.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wahyouwebid.danamontask.BuildConfig
import com.wahyouwebid.danamontask.features.auth.data.dao.UserDao
import com.wahyouwebid.danamontask.features.auth.data.entity.UserEntity

@Database(
    entities = [UserEntity::class, ],
    version = BuildConfig.VERSION_CODE,
    exportSchema = false
)
abstract class RoomDB : RoomDatabase() {

    abstract fun userDao() : UserDao

    companion object {

        @Volatile private var instance : RoomDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, RoomDB::class.java, "danamon.db")
            .fallbackToDestructiveMigration()
            .build()

    }
}