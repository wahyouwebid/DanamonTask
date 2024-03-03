package com.wahyouwebid.danamontask.features.auth.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wahyouwebid.danamontask.core.entity.UserEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface AuthDao {

    @Query("SELECT * FROM user WHERE email = :email AND password = :password")
    fun login(email: String, password: String): Single<UserEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun register(data: UserEntity): Completable

}