package com.wahyouwebid.danamontask.features.admin.data

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wahyouwebid.danamontask.core.entity.UserEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface AdminDao {

    @Query("SELECT * FROM user WHERE email =:email")
    fun getUserByEmail(email: String): Single<UserEntity>

    @Query("SELECT * FROM user ORDER BY id DESC")
    fun getAll(): PagingSource<Int, UserEntity>

    @Query("SELECT * FROM user where id =:id")
    fun getById(id: Int): Single<UserEntity>

    @Query("UPDATE user SET username = :username, email = :email, role = :role WHERE id = :id")
    fun update(id: Int, username: String, email: String, role: Int): Completable

    @Query("DELETE FROM user WHERE id = :id")
    fun delete(id: Int): Completable
}