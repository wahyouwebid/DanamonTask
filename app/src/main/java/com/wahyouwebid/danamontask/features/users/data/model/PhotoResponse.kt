package com.wahyouwebid.danamontask.features.users.data.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class PhotoResponse(
    @SerializedName("albumId")
    val albumId: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("url")
    val url: String?
)