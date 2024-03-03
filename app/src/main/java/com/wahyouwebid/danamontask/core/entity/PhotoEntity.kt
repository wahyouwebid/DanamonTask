package com.wahyouwebid.danamontask.core.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "photo")
@Parcelize
data class PhotoEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int?,
    val albumId: Int?,
    val thumbnailUrl: String?,
    val title: String?,
    val url: String?
): Parcelable