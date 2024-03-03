package com.wahyouwebid.danamontask.features.users.data.remote

import com.wahyouwebid.danamontask.features.users.data.model.PhotoResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by wahyouwebid
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 */

interface UserApiService {

    @GET("photos")
    fun getPhotos(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
    ): Single<List<PhotoResponse>>

}