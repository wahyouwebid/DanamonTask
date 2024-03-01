package com.wahyouwebid.danamontask.features.auth.data.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.wahyouwebid.danamontask.features.auth.data.entity.UserEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface UserDao {

    @Query("SELECT * FROM user WHERE email = :email AND password = :password")
    fun login(email: String, password: String): Single<UserEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun register(data: UserEntity): Completable

    @Query("SELECT * FROM user WHERE email =:email")
    fun getUserByEmail(email: String): Single<UserEntity>

    @Query("SELECT * FROM user WHERE role = 0")
    fun getAll(): PagingSource<Int, UserEntity>

    @Query("SELECT * FROM user where id =:id")
    fun getById(id: Int): Single<UserEntity>

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(data: UserEntity): Completable

    @Query("DELETE FROM user WHERE id = :id")
    fun delete(id: Int): Completable
}