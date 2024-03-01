package com.wahyouwebid.danamontask.core.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class RoomDB : RoomDatabase() {

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