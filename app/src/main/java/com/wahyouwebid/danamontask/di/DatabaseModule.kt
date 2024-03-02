package com.wahyouwebid.danamontask.di

import android.content.Context
import com.wahyouwebid.danamontask.core.dao.UserDao
import com.wahyouwebid.danamontask.core.database.RoomDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/***********************************************************************************
 * Created by Ujang Wahyu
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 ******************************************************************************************/


@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) : RoomDB = RoomDB(context)


    @Provides
    fun provideUserDao(database: RoomDB): UserDao = database.userDao()

}