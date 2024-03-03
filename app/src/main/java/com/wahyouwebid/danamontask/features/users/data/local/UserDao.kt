package com.wahyouwebid.danamontask.features.users.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wahyouwebid.danamontask.core.entity.PhotoEntity
import io.reactivex.rxjava3.core.Completable

@Dao
interface UserDao {

    @Query("SELECT * FROM photo ORDER BY albumId ASC")
    fun getAll(): PagingSource<Int, PhotoEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(populars: List<PhotoEntity>): Completable

    @Query("DELETE FROM photo")
    fun clearAll(): Completable
}