package com.wahyouwebid.danamontask.core.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "user")
@Parcelize
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val username: String,
    val role: Int, // 0 User, 1 Admin
    val email: String,
    val password: String,
): Parcelable