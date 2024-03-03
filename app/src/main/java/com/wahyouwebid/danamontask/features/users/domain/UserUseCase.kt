package com.wahyouwebid.danamontask.features.users.domain

import androidx.paging.PagingData
import com.wahyouwebid.danamontask.core.entity.PhotoEntity
import kotlinx.coroutines.CoroutineScope

/***********************************************************************************
 * Created by Ujang Wahyu
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 ******************************************************************************************/


interface UserUseCase {

    fun getPhotos(
        scope: CoroutineScope,
        callback: (PagingData<PhotoEntity>) -> Unit
    )

}